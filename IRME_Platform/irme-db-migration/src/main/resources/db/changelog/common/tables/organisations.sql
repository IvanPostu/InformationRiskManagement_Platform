
CREATE TABLE [dbo].[organisations] (

    [organisation_id]   INTEGER  IDENTITY(1, 1) NOT NULL,
    [name]              NVARCHAR (256)   NOT NULL UNIQUE,
    [description]       NVARCHAR (512)   NOT NULL DEFAULT '',
    [base64_logo]       NVARCHAR (MAX),
    [created]           DATE,
    [status]            NVARCHAR(32) NOT NULL DEFAULT 'ACTIVE',

    CONSTRAINT pk_organisations PRIMARY KEY CLUSTERED ([organisation_id] ASC)
);

