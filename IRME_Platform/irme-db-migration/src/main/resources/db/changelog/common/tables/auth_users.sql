

CREATE TABLE [dbo].[auth_users] (
  [auth_user_id]   		INTEGER  IDENTITY(1, 1) NOT NULL,
  [email_address]  		VARCHAR (128)   NOT NULL,
  [password_hash]			VARCHAR (256)   NOT NULL,
  [status]				    VARCHAR (32)    NOT NULL DEFAULT 'ACTIVE',
  [is_banned]         BIT             NOT NULL DEFAULT 0,
  
  CONSTRAINT pk_auth_users PRIMARY KEY CLUSTERED ([auth_user_id] ASC),
  CONSTRAINT unique_email_address UNIQUE ([email_address])
);

