
CREATE TABLE [dbo].[sa__question_answer_descriptions] (
    [question_answer_id]                    INTEGER NOT NULL,
    [description_if_is_present_answer]	    VARCHAR (4096) NOT NULL,
    [description_if_is_not_present_answer]	VARCHAR (4096) NOT NULL
);

