
--liquibase formatted sql
--changeset IvanPostu:test_profile/test-data-V1.0 splitStatements:true endDelimiter:GO

INSERT INTO dbo.auth_roles (role_id, role_name)
VALUES 
(3, 'ROLE_TEST');

--rollback <rollback SQL statements>
--rollback <rollback SQL statements>
