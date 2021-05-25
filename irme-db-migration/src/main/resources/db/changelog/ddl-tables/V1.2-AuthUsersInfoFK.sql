--liquibase formatted sql
--changeset IvanPostu:ddl-tables-V1.2 splitStatements:true endDelimiter:;

ALTER TABLE
    [dbo].[auth_users_info]
ADD CONSTRAINT
    fk_auth_user_info_id
FOREIGN KEY ( auth_user_id ) 
REFERENCES [dbo].[auth_users]( auth_user_id )

--rollback <rollback SQL statements>
--rollback <rollback SQL statements>
