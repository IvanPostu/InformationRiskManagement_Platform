CREATE TABLE [dbo].[sa__profiles] (
    [profile_id]                    INTEGER IDENTITY(1, 1) NOT NULL,
    [name]			                VARCHAR (128) NOT NULL UNIQUE,
    [description]                   VARCHAR (1024),
    [base64_logo]                   VARCHAR (MAX),

    CONSTRAINT pk_sa__profiles PRIMARY KEY CLUSTERED ([profile_id] ASC)
);


