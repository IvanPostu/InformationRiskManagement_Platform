
ALTER TABLE
    [dbo].[sa__questions]
DROP CONSTRAINT
    fk_category_id;


ALTER TABLE
    [dbo].[sa__questions]
DROP CONSTRAINT
    fk_question_depends_on_question_answers;


ALTER TABLE
    [dbo].[sa__processes]
DROP CONSTRAINT
    fk_organisations_sa_processes;


ALTER TABLE
    [dbo].[sa__questions_answers]
DROP CONSTRAINT
    fk_sa_questions;


ALTER TABLE
    [dbo].[sa__questions_answers]
DROP CONSTRAINT
    fk_sa_answers;


ALTER TABLE
    [dbo].[sa__questions_answers]
DROP CONSTRAINT
    fk_sa_profile_questions_answers;


ALTER TABLE
    [dbo].[sa__results]
DROP CONSTRAINT
    fk_sa_question_answer_results;


ALTER TABLE
    [dbo].[sa__results]
DROP CONSTRAINT
    fk_sa_process_results;


