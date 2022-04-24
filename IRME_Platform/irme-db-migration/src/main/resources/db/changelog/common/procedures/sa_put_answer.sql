
CREATE OR ALTER PROCEDURE [dbo].[sa_put_answer]
    @question_id 			INTEGER,
    @answer_id				INTEGER,
    @process_id				INTEGER,
    @clean_answer			BIT = 0, --if 1 delete existed one
    @isSuccess  			BIT OUTPUT
AS
BEGIN TRY 
	SET @isSuccess = 0;
	DECLARE @error_message NVARCHAR(1024) = '';	

	-- check if evaluation process exists
	IF NOT EXISTS (
		SELECT TOP 1 1 FROM sa__processes AS sp 
		WHERE sp.process_id=@process_id
	)
	BEGIN 
		SET @error_message = CONCAT(
			'Not found sa_process record with id: ', @process_id);
		RAISERROR(@error_message, 16, 1)
	END
	
    DECLARE @sa_question_answer_id INTEGER = (
        SELECT TOP 1 sqa.id  FROM sa__questions_answers AS sqa
        WHERE sqa.question_id=@question_id AND sqa.answer_id=@answer_id 
    );

    -- check if link between question and answer exists
    IF @sa_question_answer_id IS NULL
    BEGIN
        SET @error_message = CONCAT('Not found sa_question_answer record for question id: ', 
            @question_id,
            ' and answer id: ',
            @answer_id
        );
        RAISERROR(@error_message, 16, 1)
    END
	
	DECLARE @isMultiQuestion BIT = (
		SELECT TOP 1 1 
		FROM sa__questions AS sq 
		WHERE sq.question_id=@question_id AND sq.has_multiple_answers=1
	);

	IF @isMultiQuestion <> 1
	BEGIN
		-- delete sa__results row if already have answer
		DELETE sr
		FROM sa__results AS sr 
		INNER JOIN sa__questions_answers AS sqa ON sqa.id=sr.question_answer_id 
		WHERE sr.process_id=@process_id AND sqa.question_id=@question_id;
	END
	
	IF @clean_answer = 1
	BEGIN 
		DELETE sr
		FROM sa__results AS sr 
		WHERE sr.process_id=@process_id AND sr.question_answer_id=@sa_question_answer_id;
	END
	ELSE
	BEGIN
		IF NOT EXISTS (
        	SELECT TOP 1 1 FROM sa__results 
            WHERE process_id=@process_id AND question_answer_id=@sa_question_answer_id
        )
        BEGIN
			INSERT INTO sa__results (process_id, question_answer_id) 
			VALUES ( @process_id, @sa_question_answer_id );	
		END
	END

	SET @isSuccess=1;
END TRY  
BEGIN CATCH
	EXECUTE dbo.report_error;
END CATCH;
