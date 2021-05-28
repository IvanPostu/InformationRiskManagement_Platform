


	
--liquibase formatted sql
--changeset IvanPostu:ddl-data-V1.1 splitStatements:true endDelimiter:;

INSERT INTO dbo.auth_roles (role_id, role_name)
VALUES 
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

--rollback <rollback SQL statements>
--rollback <rollback SQL statements>
