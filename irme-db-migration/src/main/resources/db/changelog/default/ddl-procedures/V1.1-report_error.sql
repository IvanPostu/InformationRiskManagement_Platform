--liquibase formatted sql
--changeset IvanPostu:ddl-procedures-V1.1 splitStatements:true endDelimiter:GO context:default


CREATE OR ALTER PROCEDURE [dbo].[report_error]
AS
BEGIN
    SELECT   
        ERROR_NUMBER()      AS err_number,
        ERROR_SEVERITY()    AS err_severity,
        ERROR_STATE()       AS err_state,
        ERROR_LINE ()       AS err_line,
        ERROR_PROCEDURE()   AS err_procedure,
        ERROR_MESSAGE()     AS err_message
END;

--rollback <rollback SQL statements>
--rollback <rollback SQL statements>
