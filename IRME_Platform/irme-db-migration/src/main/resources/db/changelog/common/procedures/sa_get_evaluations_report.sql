
CREATE OR ALTER PROCEDURE [dbo].[sa_get_evaluations_report]
    @process_id			INTEGER
AS
BEGIN TRY 

	SELECT 
		answers.answer_id,
		questions.question_id,
		questions.question ,
		answers.answer,
		descriptions.description_if_is_present_answer,
		descriptions.description_if_is_not_present_answer
	FROM sa__processes AS processes
	INNER JOIN sa__results AS results ON results.process_id=processes.process_id 
	INNER JOIN sa__questions_answers AS qa ON results.question_answer_id=qa.id  
	INNER JOIN sa__question_answer_descriptions AS descriptions ON descriptions.question_answer_id=qa.id
	INNER JOIN sa__questions AS questions ON questions.question_id=qa.question_id 
	INNER JOIN sa__answers AS answers ON answers.answer_id=qa.answer_id 
	WHERE processes.process_id=@process_id;
	
END TRY  
BEGIN CATCH
	EXECUTE dbo.report_error;
END CATCH;
