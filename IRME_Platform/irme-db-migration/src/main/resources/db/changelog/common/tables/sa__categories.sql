
CREATE TABLE [dbo].[sa__categories] (

  [category_id]     INTEGER IDENTITY(1, 1) NOT NULL,
  [name]            VARCHAR (256)   NOT NULL UNIQUE,
  [description]     VARCHAR (512)   NOT NULL DEFAULT '',
  [base64_logo]     VARCHAR (MAX),
  [status]          VARCHAR(32) NOT NULL DEFAULT 'ACTIVE',

  CONSTRAINT pk_sa__categories PRIMARY KEY CLUSTERED ([category_id] ASC)
);

