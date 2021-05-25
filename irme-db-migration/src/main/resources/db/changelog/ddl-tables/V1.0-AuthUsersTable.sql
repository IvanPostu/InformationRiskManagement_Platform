--liquibase formatted sql
--changeset IvanPostu:ddl-tables-V1.0 splitStatements:true endDelimiter:;
CREATE TABLE [dbo].[auth_users] (
    [auth_user_id]   		INTEGER  IDENTITY(1, 1) NOT NULL,
    [auth_user_info_id]     INTEGER  NOT NULL,
    [email_address]  		VARCHAR (128)   NOT NULL,
    [password_hash]			VARCHAR (256)   NOT NULL,
    [status]				VARCHAR (32)    NOT NULL,
    
    CONSTRAINT pk_auth_users PRIMARY KEY CLUSTERED ([auth_user_id] ASC)
);
--rollback <rollback SQL statements>
--rollback <rollback SQL statements>
