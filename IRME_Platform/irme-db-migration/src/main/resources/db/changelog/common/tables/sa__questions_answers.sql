
CREATE TABLE [dbo].[sa__questions_answers] (
    [id]            INTEGER IDENTITY(1, 1) NOT NULL,
    [profile_id]    INTEGER NOT NULL,
    [question_id]	INTEGER NOT NULL,
    [answer_id]     INTEGER NOT NULL,
    [answer_weight] INTEGER DEFAULT 10

    CONSTRAINT pk_sa__questions_answers PRIMARY KEY CLUSTERED ([id] ASC)
);


CREATE UNIQUE INDEX uidx_sa__questions_answers
ON [dbo].[sa__questions_answers] (
    [question_id], 
    [answer_id], 
    [profile_id]
);


