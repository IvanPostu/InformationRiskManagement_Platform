
CREATE TABLE [dbo].[sa__processes] (
    [process_id]                    INTEGER IDENTITY(1, 1) NOT NULL,
    [organisation_id]			          INTEGER NOT NULL,
    [created]                       DATE,

    CONSTRAINT pk_sa__processes PRIMARY KEY CLUSTERED ([process_id] ASC)
);


