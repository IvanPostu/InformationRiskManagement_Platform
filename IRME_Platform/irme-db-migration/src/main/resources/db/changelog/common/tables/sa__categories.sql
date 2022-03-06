
CREATE TABLE [dbo].[sa__categories] (

  [category_id]     INTEGER IDENTITY(1, 1) NOT NULL,
  [name]            NVARCHAR (256)   NOT NULL UNIQUE,
  [description]     NVARCHAR (512)   NOT NULL DEFAULT '',
  [base64_logo]     NVARCHAR (MAX),
  [status]          NVARCHAR(32) NOT NULL DEFAULT 'ACTIVE',

  CONSTRAINT pk_sa__categories PRIMARY KEY CLUSTERED ([category_id] ASC)
);

