
CREATE OR ALTER PROCEDURE [dbo].[sa_put_answer]
    @question_id 			INTEGER,
    @answer_id				INTEGER,
    @process_id				INTEGER,
    @user_id				INTEGER,
    @isSuccess  			INTEGER OUTPUT
AS
BEGIN TRY 
	SET @isSuccess = -1;

	IF NOT EXISTS (
		SELECT TOP 1 1 FROM sa__processes AS sp 
		WHERE sp.process_id=@process_id AND sp.author_user_id=@user_id
	)
	BEGIN 
		RETURN
	END

	DECLARE @sa_question_answer_id INTEGER = (
		SELECT TOP 1 sqa.id  FROM sa__questions_answers AS sqa
		WHERE sqa.question_id=@question_id AND sqa.answer_id=@answer_id 
	);
	
	IF @sa_question_answer_id IS NULL AND @sa_question_answer_id > 0
	BEGIN
		RETURN
	END
	
	INSERT INTO sa__results (process_id, question_answer_id) 
	VALUES
	(@process_id, @sa_question_answer_id);
	
	SET @isSuccess=1;
	
END TRY  
BEGIN CATCH
	EXECUTE dbo.report_error
END CATCH;
