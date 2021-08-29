

CREATE OR ALTER PROCEDURE [dbo].[reassign_user_to_organisations]
	@user_id  				    INTEGER,
  @organisations_ids 	  NVARCHAR(MAX),
  @is_success  				  INTEGER OUTPUT
AS
BEGIN TRY
	DECLARE @transaction_name VARCHAR(12) = 'transq_001';
	SET @is_success = -1;

	BEGIN TRANSACTION @transaction_name;
		DELETE FROM [dbo].[organisations_users] 
		WHERE auth_user_id = @user_id;
	
		INSERT INTO [dbo].[organisations_users] 
			(auth_user_id, organisation_id)
		SELECT 
			@user_id AS auth_user_id,
			[value] AS organisation_id
		FROM STRING_SPLIT(@organisations_ids, ',')
		
		SET @is_success = 1;
	COMMIT TRANSACTION @transaction_name;
END TRY
BEGIN CATCH
	ROLLBACK TRANSACTION @transaction_name;
	EXECUTE dbo.report_error;
END CATCH 

