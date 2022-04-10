
CREATE OR ALTER PROCEDURE [dbo].[sa_categories_get]
AS
BEGIN   
    SELECT [category_id],
        [name],
        [description],
        [image_url],
        [status]
    FROM 
        [dbo].[sa__categories]
    WITH (NOLOCK);
END;

