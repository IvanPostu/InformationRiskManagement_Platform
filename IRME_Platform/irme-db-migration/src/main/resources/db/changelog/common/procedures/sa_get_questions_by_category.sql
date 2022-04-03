
CREATE OR ALTER PROCEDURE [dbo].[sa_get_questions_by_category] 
    @category_id    INTEGER
AS
BEGIN TRY
    
	DECLARE @targetQuestions TABLE (
		question_id INTEGER,
		depends_on_question_answer_id INTEGER,
		question NVARCHAR(MAX),
		has_multiple_answers BIT,
		question_weight INTEGER,
		answers_ids VARCHAR(1024)
	);

	INSERT INTO @targetQuestions 
		(question_id, 
		depends_on_question_answer_id, 
		question, 
		has_multiple_answers, 
		question_weight,
		answers_ids)
	SELECT 
	        sq.question_id, 
	        sq.depends_on_question_answer_id, 
	        sq.question, 
	        sq.has_multiple_answers, 
	        question_max_weight=(
	        	SELECT MAX(qa.answer_weight) 
	        	FROM sa__questions_answers AS qa 
	        	WHERE qa.question_id=sq.question_id 
	        	GROUP BY qa.question_id
	        ),
	        answers_ids=(
	        	SELECT STRING_AGG( ISNULL(answers.answer_id, ''), ',') 
	        	FROM sa__answers AS answers
	        	WHERE answers.answer_id IN (SELECT sqa.answer_id 
	        								FROM sa__questions_answers AS sqa 
	        								WHERE sqa.question_id=sq.question_id)
	        )
    FROM
        dbo.sa__questions AS sq
    WHERE sq.category_id=@category_id;
   
	SELECT 
		question_id, 
		depends_on_question_answer_id, 
		question, 
		has_multiple_answers, 
		question_weight AS question_max_weight,
		answers_ids
	FROM @targetQuestions;

	SELECT 
		sa.answer_id,
		sa.answer
	FROM sa__answers AS sa 
	WHERE sa.answer_id IN (
		SELECT answer_id FROM sa__questions_answers sqa 
		WHERE sqa.question_id IN (SELECT question_id FROM @targetQuestions)	
	);

END TRY
BEGIN CATCH  
	EXECUTE dbo.report_error;
END CATCH 
