--liquibase formatted sql
--changeset IvanPostu:ddl-tables-V1.1 splitStatements:true endDelimiter:;
CREATE TABLE [dbo].[auth_users_info] (
	[auth_user_id]          INTEGER  		NOT NULL,
	[first_name]     		VARCHAR (64)    NULL,
	[last_name]      		VARCHAR (64)    NULL,
	[phone]          		VARCHAR (30)    NULL,
	[country_code]   		CHAR (2)        NULL,
	[create_date]    		DATETIME        NOT NULL,
	
	CONSTRAINT pk_auth_users_info PRIMARY KEY (auth_user_id)
);
--rollback <rollback SQL statements>
--rollback <rollback SQL statements>