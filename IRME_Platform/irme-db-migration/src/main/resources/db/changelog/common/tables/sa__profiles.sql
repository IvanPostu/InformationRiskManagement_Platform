CREATE TABLE [dbo].[sa__profiles] (
    [profile_id]                    INTEGER IDENTITY(1, 1) NOT NULL,
    [name]			                NVARCHAR (128) NOT NULL UNIQUE,
    [description]                   NVARCHAR (1024),
    [base64_logo]                   NVARCHAR (MAX),

    CONSTRAINT pk_sa__profiles PRIMARY KEY CLUSTERED ([profile_id] ASC)
);


