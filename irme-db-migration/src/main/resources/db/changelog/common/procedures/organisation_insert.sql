
CREATE OR ALTER PROCEDURE [dbo].[organisation_insert]
	@name  				              VARCHAR(256),
  @description  		    	    VARCHAR(512),
  @base64_picture  			      VARCHAR(MAX),
  @inserted_organisation_id  	INTEGER OUTPUT
AS
BEGIN TRY
	DECLARE @transaction_name VARCHAR(12) = 'transq_001';
	SET @inserted_organisation_id = -1;

	BEGIN TRANSACTION @transaction_name;
	
	INSERT INTO [dbo].[organisations] 
		(name, description, base64_logo, created, status)
	VALUES
		(@name, @description, @base64_picture, GETDATE(), 'ACTIVE');
	
	SET @inserted_organisation_id = @@IDENTITY;
	COMMIT TRANSACTION @transaction_name;
END TRY
BEGIN CATCH
	ROLLBACK TRANSACTION @transaction_name;
	EXECUTE dbo.report_error;
END CATCH 

