

CREATE OR ALTER PROCEDURE [dbo].[sa_insert_answer_and_description]
    @question_id INTEGER,
    @profile_id INTEGER,
    @answer_text_param VARCHAR(1024),
    @description_if_is_present_answer VARCHAR(4096),
    @description_if_is_not_present_answer VARCHAR(4096),
    @inserted_question_answer_id INTEGER OUTPUT
AS
BEGIN TRY
    DECLARE @transaction_name VARCHAR(12) = 'transq_001';
    DECLARE @questions_answers_id INTEGER = NULL;
	DECLARE @answer_id INTEGER = (
		SELECT TOP 1
			id
		FROM
			security_assessment_answers
		WHERE
			answer = answer_text_param
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
        (question_id, answer_id, profile_id)
            VALUES
        (@question_id, @answer_id, @profile_id);
        SET @questions_answers_id=@@IDENTITY;

        INSERT INTO [dbo].[sa__question_answer_descriptions]
        (
            question_answer_id,
            description_if_is_present_answer,
            description_if_is_not_present_answer
        )
        VALUES
        (
            @questions_answers_id,
            @description_if_is_present_answer,
            @description_if_is_not_present_answer
        );

        SET @inserted_question_answer_id = @questions_answers_id;
    COMMIT TRANSACTION @transaction_name;
END TRY
BEGIN CATCH  
	ROLLBACK TRANSACTION @transaction_name;
	EXECUTE dbo.report_error;
END CATCH 
