--liquibase formatted sql
--changeset IvanPostu:ddl-tables-V1.4 splitStatements:true endDelimiter:;

ALTER TABLE
    [dbo].[auth_users_info]
ADD CONSTRAINT
    fk_country_code
FOREIGN KEY ([country_code]) 
REFERENCES [dbo].[countries]([country_code])

--rollback <rollback SQL statements>
--rollback <rollback SQL statements>
