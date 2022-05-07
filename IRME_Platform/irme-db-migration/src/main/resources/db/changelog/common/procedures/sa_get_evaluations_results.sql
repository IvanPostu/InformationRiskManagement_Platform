
CREATE OR ALTER PROCEDURE [dbo].[sa_get_evaluations_results]
    @organisation_id 			INTEGER,
    @category_id 				INTEGER = -1,
    @limits_per_category        INTEGER = 5
AS
BEGIN TRY 
    DROP TABLE IF EXISTS #categories_with_max_weight;

	CREATE TABLE #categories_with_max_weight (
		category_id INTEGER,
		max_weight INTEGER
	);

	INSERT INTO #categories_with_max_weight (category_id, max_weight)
	SELECT category_id, SUM(max_weight) AS max_weight
	FROM (
		SELECT 
			IIF(questions.has_multiple_answers=0, MAX(qa.answer_weight), SUM(qa.answer_weight)) AS max_weight,
			questions.has_multiple_answers AS has_multiple_answers,
			questions.category_id 
		FROM sa__questions AS questions
		INNER JOIN sa__questions_answers AS qa ON qa.question_id=questions.question_id 
		GROUP BY qa.question_id, questions.has_multiple_answers, questions.category_id 
	) inner_query
	GROUP BY inner_query.category_id

	
	DECLARE @result_query NVARCHAR(MAX) = CONCAT('
		SELECT inner_query.* FROM (
			SELECT 
				process_id 			 = sp.process_id,
	            category_id          = sp.category_id,
				created    			 = MAX(sp.created),
				answers_total_weight = SUM(qa.answer_weight),
				answer_max_weight 	 = (SELECT TOP 1 max_weight FROM #categories_with_max_weight WHERE category_id=sp.category_id),
				status_code 		 = MAX(sp.status),
				rn = ROW_NUMBER() OVER(PARTITION BY sp.[category_id]
				                          ORDER BY MAX(sp.[created]) DESC)
			FROM sa__processes AS sp
			INNER JOIN sa__results AS sr ON sr.process_id=sp.process_id  
			INNER JOIN sa__questions_answers AS qa ON qa.id=sr.question_answer_id 
			WHERE ', IIF(@category_id=-1, '', 'sp.category_id=@category_id AND '), ' sp.organisation_id=@organisation_id
			GROUP BY sp.process_id, sp.category_id
		) AS inner_query WHERE inner_query.rn <= @limits_per_category
	');

	EXECUTE sp_executesql @result_query,
		N'@organisation_id INTEGER, @category_id INTEGER, @limits_per_category INTEGER',
		@organisation_id, @category_id, @limits_per_category;
	
END TRY  
BEGIN CATCH
	EXECUTE dbo.report_error
END CATCH;

