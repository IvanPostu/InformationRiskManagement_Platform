--liquibase formatted sql
--changeset IvanPostu:ddl-procedures-V1.7 splitStatements:true endDelimiter:GO context:default


CREATE OR ALTER PROCEDURE [dbo].[auth_user_with_info_delete]
	@user_id INTEGER
AS
BEGIN TRY  
	DECLARE @transaction_name VARCHAR(12) = 'transq_001';
	BEGIN TRANSACTION @transaction_name;
	
    DELETE FROM dbo.auth_user_roles
    WHERE auth_user_id = @user_id;
      
    DELETE FROM dbo.auth_users_info 
    WHERE auth_user_id = @user_id; 
      
    DELETE FROM dbo.auth_users 
    WHERE auth_user_id = @user_id;
			
	
	COMMIT TRANSACTION @transaction_name;
END TRY  
BEGIN CATCH  
	ROLLBACK TRANSACTION @transaction_name;
	EXECUTE dbo.report_error;
END CATCH 


--rollback <rollback SQL statements>
--rollback <rollback SQL statements>
