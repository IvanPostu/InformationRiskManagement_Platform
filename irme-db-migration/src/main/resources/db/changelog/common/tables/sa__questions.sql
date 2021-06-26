
CREATE TABLE [dbo].[sa__questions] (
    [question_id]                   INTEGER IDENTITY(1, 1) NOT NULL,
    [category_id]                   INTEGER NOT NULL,
    [depends_on_question_answer_id] INTEGER DEFAULT NULL, -- default value -> null it's important!!!
    [question]			                VARCHAR (1024) NOT NULL,
    [has_multiple_answers]          BIT DEFAULT 0,

    CONSTRAINT pk_sa__questions PRIMARY KEY CLUSTERED ([question_id] ASC)
);


