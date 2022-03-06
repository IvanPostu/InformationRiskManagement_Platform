

CREATE OR ALTER PROCEDURE [dbo].[sa_insert_question] 
    @category_name                          NVARCHAR(256),
  	@question                               NVARCHAR(1024),
  	@question_has_multiple_answers          BIT,
    @depends_on_inserted_question_answer_id INTEGER, -- CAN BE NULL
    @last_inserted_question_id              INTEGER OUTPUT
AS
BEGIN TRY
    DECLARE @transaction_name NVARCHAR(12) = 'transq_001';
    DECLARE @category_id INTEGER = (
	    SELECT TOP 1
	        category_id
	    FROM
	        [dbo].[sa__categories]
	    WHERE
	        [name] = @category_name
	);

    BEGIN TRANSACTION @transaction_name;
        IF @category_id IS NULL
        BEGIN
            RAISERROR (
                'Category no found.',
                16, 
                1 
            );  
        END;

        INSERT INTO [dbo].[sa__questions] (
            [category_id],
            [depends_on_question_answer_id],
            [question],
            [has_multiple_answers]
        ) VALUES (
            @category_id,
            @depends_on_inserted_question_answer_id,
            @question,
            @question_has_multiple_answers
        );

        SET @last_inserted_question_id = @@IDENTITY;
    COMMIT TRANSACTION @transaction_name;
END TRY
BEGIN CATCH  
	ROLLBACK TRANSACTION @transaction_name;
	EXECUTE dbo.report_error;
END CATCH 

