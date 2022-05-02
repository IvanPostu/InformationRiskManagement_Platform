
CREATE OR ALTER PROCEDURE [dbo].[sa_get_process_answered_questions] 
    @process_id    		INTEGER,
    @process_exists		BIT OUTPUT
AS
BEGIN TRY
    
	SET @process_exists = IIF(EXISTS (
		SELECT TOP 1 1 FROM sa__results AS sr WHERE sr.process_id=@process_id
	), 1, 0)


	SELECT  
		question_answer_id = qa.id,
		question_id = qa.question_id,
		answer_id = qa.answer_id 
	FROM sa__questions_answers AS qa
	INNER JOIN sa__results AS sr ON sr.question_answer_id=qa.id 
	WHERE sr.process_id=@process_id;

END TRY
BEGIN CATCH  
	EXECUTE dbo.report_error;
END CATCH 

