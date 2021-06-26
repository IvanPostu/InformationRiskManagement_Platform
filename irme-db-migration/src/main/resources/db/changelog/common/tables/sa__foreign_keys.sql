
-- One category -> many questions 
ALTER TABLE
    [dbo].[sa__questions]
ADD CONSTRAINT
    fk_category_id
FOREIGN KEY ([category_id]) 
REFERENCES [dbo].[sa__categories]([category_id]);


/*
Situations in which the question depends on the question-answer link.
For example, if a user answers a question, then an additional question will
be displayed to him, which depends on the previous answer.
*/
ALTER TABLE
    [dbo].[sa__questions]
ADD CONSTRAINT
    fk_question_depends_on_question_answers
FOREIGN KEY ([depends_on_question_answer_id]) 
REFERENCES [dbo].[sa__questions_answers]([id]);


ALTER TABLE
    [dbo].[sa__processes]
ADD CONSTRAINT
    fk_organisations_sa_processes
FOREIGN KEY ([organisation_id]) 
REFERENCES [dbo].[organisations]([organisation_id]);


-- ManyToMany: Questions -> Answers
ALTER TABLE
    [dbo].[sa__questions_answers]
ADD CONSTRAINT
    fk_sa_questions
FOREIGN KEY ([question_id]) 
REFERENCES [dbo].[sa__questions]([question_id]);


-- ManyToMany: Questions -> Answers
ALTER TABLE
    [dbo].[sa__questions_answers]
ADD CONSTRAINT
    fk_sa_answers
FOREIGN KEY ([answer_id]) 
REFERENCES [dbo].[sa__answers]([answer_id]);


-- OneToMany: Profile -> QuestionsAnswers
ALTER TABLE
    [dbo].[sa__questions_answers]
ADD CONSTRAINT
    fk_sa_profile_questions_answers
FOREIGN KEY ([profile_id]) 
REFERENCES [dbo].[sa__profiles]([profile_id]);


ALTER TABLE
    [dbo].[sa__results]
ADD CONSTRAINT
    fk_sa_question_answer_results
FOREIGN KEY ([question_answer_id]) 
REFERENCES [dbo].[sa__questions_answers]([id]);


ALTER TABLE
    [dbo].[sa__results]
ADD CONSTRAINT
    fk_sa_process_results
FOREIGN KEY ([process_id]) 
REFERENCES [dbo].[sa__processes]([process_id]);







