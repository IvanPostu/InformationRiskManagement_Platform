
CREATE OR ALTER PROCEDURE [dbo].[dev__evaluate_organisation]
	@organisation_id 		INTEGER,
	@category_id 			INTEGER,
	@user_id 				INTEGER,
	@evaluation_date 		DATETIME
AS
BEGIN TRY 

	DECLARE @evaluation_process_id INTEGER
	EXECUTE sa_create_evaluation_process @organisation_id, @user_id , @category_id,  @evaluation_process_id OUTPUT;
	
	PRINT CONCAT('Evaluation process created with id: ', @evaluation_process_id);

	UPDATE e 
	SET e.created=@evaluation_date
	FROM sa__processes AS e 
	WHERE e.process_id=@evaluation_process_id;

	DECLARE @targetQuestions TABLE (
		question_id INTEGER,
		answers_ids NVARCHAR(MAX)
	);
	
	INSERT INTO @targetQuestions (question_id, answers_ids)
	SELECT 
        sq.question_id, 
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
	   
	DECLARE @loopCounter INTEGER = (SELECT COUNT(*) FROM @targetQuestions)
	WHILE @loopCounter > 0
	BEGIN
		SET @loopCounter = @loopCounter - 1
		
		DECLARE @answers_ids NVARCHAR(MAX);
		DECLARE @question_id INTEGER;
		DECLARE @random_answer_id INTEGER;
		
		SELECT 
			@question_id = t.question_id, 
			@answers_ids = t.answers_ids 
		FROM @targetQuestions AS t
		ORDER BY t.question_id
		OFFSET @loopCounter ROWS
		FETCH NEXT 1 ROWS ONLY;
	
		EXECUTE dev__random_item @answers_ids, @random_answer_id OUTPUT;
		
		PRINT CONCAT('Question: ', @question_id, ' Answers: ', @answers_ids);
	
		DECLARE @isSuccess BIT
		EXECUTE sa_put_answer @question_id, @random_answer_id, @evaluation_process_id, 0, @isSuccess OUTPUT;
		
		PRINT CONCAT(IIF(@isSuccess=1, '+', '?'), ' Question: ', @question_id, ' Answer: ', @random_answer_id);
		
	END


END TRY  
BEGIN CATCH
	EXECUTE dbo.report_error;
END CATCH;

