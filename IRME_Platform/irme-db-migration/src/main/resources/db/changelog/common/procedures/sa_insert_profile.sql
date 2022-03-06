
CREATE OR ALTER PROCEDURE [dbo].[sa_insert_profile] 
    @profile_name			  NVARCHAR (128),
    @profile_description      NVARCHAR (1024),
    @base64_logo              NVARCHAR (MAX),
    @inserted_id              INTEGER OUTPUT
AS
BEGIN TRY
    DECLARE @transaction_name NVARCHAR(12) = 'transq_001';
    BEGIN TRANSACTION @transaction_name;
        INSERT INTO [dbo].[sa__profiles] (
            [name],
            [description],
            [base64_logo]
        ) VALUES (
            @profile_name,
            @profile_description,
            @base64_logo
        );

        SET @inserted_id = @@IDENTITY;
    COMMIT TRANSACTION @transaction_name;
END TRY
BEGIN CATCH  
	ROLLBACK TRANSACTION @transaction_name;
	EXECUTE dbo.report_error;
END CATCH 

