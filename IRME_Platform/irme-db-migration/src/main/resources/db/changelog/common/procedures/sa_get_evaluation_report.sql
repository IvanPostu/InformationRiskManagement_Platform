
CREATE OR ALTER PROCEDURE [dbo].[sa_get_evaluation_report]
    @process_id			INTEGER
AS
BEGIN TRY 

    DECLARE @category_id INTEGER = (
        SELECT TOP 1 sp.category_id  FROM sa__processes AS sp WHERE sp.process_id=@process_id
    );

    IF @category_id IS NULL
        RETURN;

    DECLARE @max_category_weight INTEGER = (
        SELECT SUM(max_weight) FROM (
            SELECT 
                IIF(questions.has_multiple_answers=0, MAX(qa.answer_weight), SUM(qa.answer_weight)) AS max_weight,
                questions.has_multiple_answers AS has_multiple_answers,
                questions.category_id 
            FROM sa__questions AS questions
            INNER JOIN sa__questions_answers AS qa ON qa.question_id=questions.question_id 
            WHERE questions.category_id=@category_id
            GROUP BY qa.question_id, questions.has_multiple_answers, questions.category_id 
        ) AS inner_query
    );

    DECLARE @total_process_weight INTEGER = (
        SELECT 
            answers_total_weight = SUM(qa.answer_weight)
        FROM sa__processes AS sp
        INNER JOIN sa__results AS sr ON sr.process_id=sp.process_id  
        INNER JOIN sa__questions_answers AS qa ON qa.id=sr.question_answer_id 
        WHERE sp.process_id=@process_id
    );

	SELECT 
		answers.answer_id,
		questions.question_id,
		questions.question ,
		answers.answer,
		descriptions.description
	FROM sa__processes AS processes
	INNER JOIN sa__results AS results ON results.process_id=processes.process_id 
	INNER JOIN sa__questions_answers AS qa ON results.question_answer_id=qa.id  
	INNER JOIN sa__question_answer_descriptions AS descriptions ON descriptions.question_answer_id=qa.id
	INNER JOIN sa__questions AS questions ON questions.question_id=qa.question_id 
	INNER JOIN sa__answers AS answers ON answers.answer_id=qa.answer_id 
	WHERE processes.process_id=@process_id;


    SELECT 
        max_category_weight = @max_category_weight,
        total_process_weight = @total_process_weight;
END TRY  
BEGIN CATCH
	EXECUTE dbo.report_error;
END CATCH;
