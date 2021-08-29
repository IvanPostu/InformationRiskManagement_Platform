
ALTER TABLE
    [dbo].[sa__question_answer_descriptions]
ADD CONSTRAINT
    fk_sa__question_answer_descriptions_question_answer
FOREIGN KEY ([question_answer_id]) 
REFERENCES [dbo].[sa__questions_answers]([id]);
