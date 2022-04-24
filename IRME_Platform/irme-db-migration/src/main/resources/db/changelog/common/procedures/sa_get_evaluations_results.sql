
CREATE OR ALTER PROCEDURE [dbo].[sa_get_evaluations_results]
    @organisation_id 			INTEGER,
    @category_id 				INTEGER
AS
BEGIN TRY 

	-- select max weight for that category evaluation
	DECLARE @current_category_max_weight INTEGER = (
		SELECT SUM(max_weight) AS max_evaluation_weight FROM (
			SELECT 
				IIF(questions.has_multiple_answers=0, MAX(qa.answer_weight), SUM(qa.answer_weight)) AS max_weight,
				questions.has_multiple_answers AS has_multiple_answers
			FROM sa__questions AS questions
			INNER JOIN sa__questions_answers AS qa ON qa.question_id=questions.question_id 
			WHERE questions.category_id=1
			GROUP BY qa.question_id, questions.has_multiple_answers

		) inner_query
	);

    SELECT @current_category_max_weight AS current_category_max_weight;

	SELECT 
		process_id 			 = sp.process_id,
		created    			 = MAX(sp.created),
		answers_total_weight = SUM(qa.answer_weight),
		status_code = MAX(sp.status) 
	FROM sa__processes AS sp
	INNER JOIN sa__results AS sr ON sr.process_id=sp.process_id  
	INNER JOIN sa__questions_answers AS qa ON qa.id=sr.question_answer_id 
	WHERE sp.category_id=@category_id
		AND sp.organisation_id=@organisation_id
	GROUP BY sp.process_id
	ORDER BY sp.process_id;
	
END TRY  
BEGIN CATCH
	EXECUTE dbo.report_error
END CATCH;



