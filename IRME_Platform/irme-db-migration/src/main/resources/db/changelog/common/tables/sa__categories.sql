
CREATE TABLE [dbo].[sa__categories] (

  [category_id]     INTEGER IDENTITY(1, 1) NOT NULL,
  [name]            NVARCHAR (256)   NOT NULL UNIQUE,
  [description]     NVARCHAR (MAX)   NOT NULL DEFAULT '',
  [image_url]       VARCHAR (2048),
  [status]          NVARCHAR(32) NOT NULL DEFAULT 'ACTIVE',

  CONSTRAINT pk_sa__categories PRIMARY KEY CLUSTERED ([category_id] ASC)
);

