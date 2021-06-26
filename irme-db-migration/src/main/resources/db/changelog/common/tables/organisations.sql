
CREATE TABLE [dbo].[organisations] (

    [organisation_id]   INTEGER  IDENTITY(1, 1) NOT NULL,
    [name]              VARCHAR (256)   NOT NULL UNIQUE,
    [description]       VARCHAR (512)   NOT NULL DEFAULT '',
    [base64_logo]       VARCHAR (MAX),
    [created]           DATE,
    [status]            VARCHAR(32) NOT NULL DEFAULT 'ACTIVE',

    CONSTRAINT pk_organisations PRIMARY KEY CLUSTERED ([organisation_id] ASC)
);

