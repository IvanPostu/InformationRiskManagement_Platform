
CREATE TABLE [dbo].[auth_users_info] (
	[auth_user_id]          INTEGER  		NOT NULL,
	[first_name]     		NVARCHAR (64)   NULL,
	[last_name]      		NVARCHAR (64)   NULL,
	[phone]          		NVARCHAR (30)   NULL,
	[country_code]   		CHAR (2)        NULL,
	[create_date]    		DATETIME        NOT NULL,
    [base64_picture]        VARCHAR(MAX)    NULL
	
	CONSTRAINT pk_auth_users_info PRIMARY KEY (auth_user_id)
);
