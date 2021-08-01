
CREATE OR ALTER PROCEDURE [dbo].[add_user_to_organisation]
	@user_id  				    INTEGER,
    @organisation_id  		    INTEGER,
    @is_success  				INTEGER OUTPUT
AS
BEGIN TRY
	DECLARE @transaction_name VARCHAR(12) = 'transq_001';
	SET @is_success = -1;

	BEGIN TRANSACTION @transaction_name;
	
	INSERT INTO [dbo].[organisations_users] 
		(auth_user_id, organisation_id)
	VALUES 
		(@user_id, @organisation_id);
	
	SET @is_success = 1;
	COMMIT TRANSACTION @transaction_name;
END TRY
BEGIN CATCH
	ROLLBACK TRANSACTION @transaction_name;
	EXECUTE dbo.report_error;
END CATCH 
