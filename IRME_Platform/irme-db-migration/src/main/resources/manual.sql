


-- CREATE DATABASE InformationRiskManagementDatabase;
-- CREATE DATABASE InformationRiskManagementDatabase_Test;

BEGIN
    INSERT INTO sa__categories ([name], [description])
    VALUES 
    (N'Protecție perimetrală', ''),
    (N'Verificarea autenticității', '');
END
BEGIN

DECLARE @current_category NVARCHAR(MAX) = N'Protecție perimetrală';
DECLARE @last_inserted_question_id INTEGER;


EXECUTE [dbo].[sa_insert_question] 
    @current_category,
    N'În organizație se utilizează la granițele rețelei ecrane inter-rețea sau alte elemente de gestiune a accesului la nivel de rețea pentru protecția resurselor corporative?',
    0,
    NULL,
    @last_inserted_question_id OUTPUT;

END



