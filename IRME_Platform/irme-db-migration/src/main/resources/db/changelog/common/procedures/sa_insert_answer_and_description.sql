

CREATE OR ALTER PROCEDURE [dbo].[sa_insert_answer_and_description]
    @question_id INTEGER,
    @profile_id INTEGER,
    @answer_text_param NVARCHAR(1024),
    @description NVARCHAR(4000),
    @answer_weight INTEGER,
    @inserted_question_answer_id INTEGER OUTPUT
AS
BEGIN TRY
    DECLARE @transaction_name NVARCHAR(12) = 'transq_001';
    DECLARE @questions_answers_id INTEGER = NULL;
	DECLARE @answer_id INTEGER = (
		SELECT TOP 1
			answer_id
		FROM
			sa__answers
		WHERE
			answer = @answer_text_param
	);

    BEGIN TRANSACTION @transaction_name;
        IF (@answer_id IS NULL)
        BEGIN
            INSERT INTO sa__answers (answer)
                VALUES
            (@answer_text_param);

            SET @answer_id=@@IDENTITY;
        END;

        INSERT INTO [dbo].[sa__questions_answers]
        (question_id, answer_id, profile_id, answer_weight)
            VALUES
        (@question_id, @answer_id, @profile_id, @answer_weight);
        SET @questions_answers_id=@@IDENTITY;

        INSERT INTO [dbo].[sa__question_answer_descriptions]
        (
            question_answer_id,
            description
        )
        VALUES
        (
            @questions_answers_id,
            @description
        );

        SET @inserted_question_answer_id = @questions_answers_id;
    COMMIT TRANSACTION @transaction_name;
END TRY
BEGIN CATCH  
	ROLLBACK TRANSACTION @transaction_name;
	EXECUTE dbo.report_error;
END CATCH 
