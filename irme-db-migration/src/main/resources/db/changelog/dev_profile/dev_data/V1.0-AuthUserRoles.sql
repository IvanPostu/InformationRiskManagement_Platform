
--liquibase formatted sql
--changeset IvanPostu:dev-data-V1.0 splitStatements:true endDelimiter:GO context:dev

INSERT INTO dbo.auth_roles (role_id, role_name)
VALUES 
(636, 'ROLE_DEV');

--rollback <rollback SQL statements>
--rollback <rollback SQL statements>
