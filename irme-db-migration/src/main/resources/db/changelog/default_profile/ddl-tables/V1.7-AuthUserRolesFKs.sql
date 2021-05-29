--liquibase formatted sql
--changeset IvanPostu:ddl-tables-V1.7 splitStatements:true endDelimiter:GO context:default

ALTER TABLE
    [dbo].[auth_user_roles]
ADD CONSTRAINT
    fk_auth_user_id
FOREIGN KEY ([auth_user_id]) 
REFERENCES [dbo].[auth_users]([auth_user_id])

ALTER TABLE
    [dbo].[auth_user_roles]
ADD CONSTRAINT
    fk_role_id
FOREIGN KEY ([role_id]) 
REFERENCES [dbo].[auth_roles]([role_id])

--rollback <rollback SQL statements>
--rollback <rollback SQL statements>
