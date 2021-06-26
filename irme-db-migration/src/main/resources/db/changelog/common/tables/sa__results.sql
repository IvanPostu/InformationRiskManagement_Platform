
-- Inermediate table for many to many relationship
CREATE TABLE [dbo].[sa__results] (
    [process_id]                  INTEGER NOT NULL,
    [question_answer_id]			    INTEGER NOT NULL,

    CONSTRAINT [pk_sa__results] 	PRIMARY KEY CLUSTERED ( [process_id], [question_answer_id] )
);




