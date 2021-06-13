

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
