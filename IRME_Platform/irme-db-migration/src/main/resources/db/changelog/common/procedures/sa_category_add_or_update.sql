
CREATE OR ALTER PROCEDURE [dbo].[sa_category_add_or_update]
    @category_id            INTEGER, -- IF IS NULL INSERT or else UPDATE 
    @name  				    NVARCHAR (256),
    @description            NVARCHAR (MAX),
    @image_url              NVARCHAR (MAX),
    @status                 NVARCHAR (32) = 'ACTIVE',
    @inserted_category_id   INTEGER OUTPUT -- greater than 0 in success case
AS
BEGIN TRY  
	DECLARE @transaction_name NVARCHAR(12) = 'transq_001';
    SET @inserted_category_id = -1;

	BEGIN TRANSACTION @transaction_name
	
        IF @category_id IS NULL
        BEGIN
            INSERT INTO [dbo].[sa__categories] (
                [name],
                [description],
                [image_url],
                [status]
            ) VALUES (@name, @description, @image_url, @status);

            SET @inserted_category_id = @@IDENTITY;
        END
            ELSE
        BEGIN
            UPDATE [dbo].[sa__categories]
            SET 
                [name] = @name,
                [description] = @description,
                [image_url] = @image_url,
                [status] = @status
            WHERE [category_id] = @category_id;

            SET @inserted_category_id = @category_id;
        END;
	
	COMMIT TRANSACTION @transaction_name
END TRY  
BEGIN CATCH  
	ROLLBACK TRANSACTION @transaction_name
	EXECUTE dbo.report_error
END CATCH 

