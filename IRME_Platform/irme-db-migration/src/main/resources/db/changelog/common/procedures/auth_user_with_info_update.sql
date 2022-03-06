
CREATE OR ALTER PROCEDURE [dbo].[auth_user_with_info_update]
	  @user_id					        INTEGER,
    @email  				          NVARCHAR(128),
    @password_hash  		    	NVARCHAR(256),
    @is_banned 						    BIT,
    @status  				          NVARCHAR(32),
    @roles					          NVARCHAR(512),
    @roles_split_separator		CHAR(1),
    @first_name  			      	NVARCHAR(64),
    @last_name  			      	NVARCHAR(64),
    @phone  				          NVARCHAR(30),
    @base64_picture           NVARCHAR(MAX),
    @country_code  			    	NVARCHAR(2)
AS
BEGIN TRY  
	DECLARE @transaction_name nVARCHAR(12) = 'transq_001';
	BEGIN TRANSACTION @transaction_name;
	
		UPDATE au 
			SET 
				email_address = @email,
				password_hash = @password_hash,
				[status] = @status,
				is_banned = @is_banned
		FROM dbo.auth_users AS au
		WHERE auth_user_id = @user_id;
	
		UPDATE ai 
		SET 
			country_code = @country_code,
			first_name = @first_name,
			last_name = @last_name,
			phone = @phone,
      base64_picture = @base64_picture
		FROM dbo.auth_users_info AS ai
		WHERE auth_user_id = @user_id;
           
    DELETE FROM dbo.auth_user_roles
    WHERE auth_user_id = @user_id;
		
		INSERT INTO dbo.auth_user_roles (auth_user_id, role_id)
		SELECT @user_id AS us_id, r.role_id 
		FROM dbo.auth_roles AS r
		WHERE r.role_name 
		IN (
			SELECT value FROM STRING_SPLIT(@roles, @roles_split_separator)
		);
	
	COMMIT TRANSACTION @transaction_name;
END TRY  
BEGIN CATCH  
	ROLLBACK TRANSACTION @transaction_name;
	EXECUTE dbo.report_error;
END CATCH 
