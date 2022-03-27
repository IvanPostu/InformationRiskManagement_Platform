USE InformationRiskManagementDatabase;

CREATE DATABASE InformationRiskManagementDatabase_Test



SELECT DISTINCT TOP 100 
	questions.question_id,
	questions.question,
	answ.answer,
	answ.answer_weight
FROM sa__questions AS questions
INNER JOIN sa__questions_answers AS qa ON qa.question_id=questions.question_id 
INNER JOIN sa__answers AS answ ON answ.answer_id=qa.answer_id 




SELECT TOP 100 * FROM sa__questions_answers ;


execute sa_categories_get


print 'În organiza?ie se utilizeaz? la grani?ele re?elei ecrane inter-re?ea sau alte elemente de gestiune a accesului la nivel de re?ea pentru protec?ia resurselor corporative?'


--====================================================

BEGIN

DECLARE @current_category NVARCHAR(MAX) = N'Protecție perimetrală';
DECLARE @last_inserted_question_id INTEGER;
DECLARE @inserted_question_answer_id INTEGER;
DECLARE @mid_sized_profile_id INT = (SELECT TOP 1 profile_id FROM sa__profiles sp WHERE sp.name='Mid sized company profile');


-- question:
EXECUTE [dbo].[sa_insert_question] 
    @current_category,
    N'Sunt plasate servicii legate cu Internetul în organizație?',
    0,
    NULL,
    10,
    @last_inserted_question_id OUTPUT;
-- answers:
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Da',
	N'Dacă serviciile legate cu Internetul sunt plasate în rețeaua internă a companiei, este necesar de convins în faptul că sunt instalate ecrane inter-rețea, rețeaua este segmentată și sunt prezente sisteme de detectare a intruziumii (I.D.S.) pentru protecția infrastructurii organizației de atacuri din Internet.',
    '',
    10,
    @inserted_question_answer_id OUTPUT; -- out
EXECUTE [sa_insert_answer_and_description] 
	@last_inserted_question_id,
	@mid_sized_profile_id,
	N'Nu',
	N'Dacă în viitor v-or fi instalate servicii legate cu Internetul în rețeaua internă a companiei, v-a fi necesar de convins în faptul că sunt instalate ecrane inter-rețea, rețeaua este segmentată și sunt prezente sisteme de detectare a intruziumii (I.D.S.) pentru protecția infrastructurii organizației de atacuri din Internet.',
    '',
    2,
    @inserted_question_answer_id OUTPUT; -- out


END





