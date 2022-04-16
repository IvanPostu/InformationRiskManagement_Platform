
CREATE TABLE [dbo].[sa__processes] (
    [process_id]                    INTEGER IDENTITY(1, 1) NOT NULL,
    [organisation_id]			    INTEGER NOT NULL,
    [created]                       DATE,
    [author_user_id]                INTEGER,

    --0:open, 1:completed, 2:force_closed
    [status]                        INTEGER DEFAULT 0,
    [category_id]                   INTEGER,

    CONSTRAINT pk_sa__processes PRIMARY KEY CLUSTERED ([process_id] ASC)
);


