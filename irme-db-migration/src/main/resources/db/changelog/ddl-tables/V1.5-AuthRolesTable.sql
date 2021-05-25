--liquibase formatted sql
--changeset IvanPostu:ddl-tables-V1.5 splitStatements:true endDelimiter:;

CREATE TABLE [dbo].[auth_roles] (
    [role_id]      INTEGER         NOT NULL,
    [role_name]    VARCHAR(32)	   NOT NULL,
    CONSTRAINT [pk_auth_roles] 	   PRIMARY KEY CLUSTERED ([role_id] ASC)
);

--rollback <rollback SQL statements>
--rollback <rollback SQL statements>
