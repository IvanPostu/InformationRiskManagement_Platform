
CREATE OR ALTER PROCEDURE [dbo].[sa_categories_get]
AS
BEGIN   
    SELECT [category_id],
        [name],
        [description],
        [base64_logo],
        [status]
    FROM 
        [dbo].[sa__categories]
    WITH (NOLOCK);
END;

