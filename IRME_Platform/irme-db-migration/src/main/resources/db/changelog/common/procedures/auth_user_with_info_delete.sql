
CREATE OR ALTER PROCEDURE [dbo].[auth_user_with_info_delete]
	@user_id INTEGER
AS
BEGIN TRY  
  SET NOCOUNT OFF ;
	DECLARE @transaction_name VARCHAR(12) = 'transq_001';
  DECLARE @deleted_rows_count INTEGER = 0;
	BEGIN TRANSACTION @transaction_name;
	
    DELETE FROM dbo.auth_user_roles
    WHERE auth_user_id = @user_id;
      
    DELETE FROM dbo.auth_users_info 
    WHERE auth_user_id = @user_id; 
      
    DELETE FROM dbo.auth_users 
    WHERE auth_user_id = @user_id;
			
    SELECT @deleted_rows_count = @@ROWCOUNT;

    IF @deleted_rows_count = 0
    BEGIN
      RAISERROR ('There was no deletion ',
        16, 
        1  
      );  
    END;
	
	COMMIT TRANSACTION @transaction_name;
END TRY  
BEGIN CATCH  
	ROLLBACK TRANSACTION @transaction_name;
	EXECUTE dbo.report_error;
END CATCH 
