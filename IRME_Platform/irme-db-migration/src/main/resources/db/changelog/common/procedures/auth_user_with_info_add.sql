
CREATE OR ALTER PROCEDURE [dbo].[auth_user_with_info_add]
    @email  				        NVARCHAR(128),
    @password_hash  		    NVARCHAR(256),
    @status  				        NVARCHAR(32) ,
    @roles					        NVARCHAR(512),
    @roles_split_separator	CHAR(1),
    @first_name  			      NVARCHAR(64),
    @last_name  			      NVARCHAR(64),
    @phone  				        NVARCHAR(30),
    @country_code  			    NVARCHAR(2),
    @base64_picture  			  NVARCHAR(MAX),
    @inserted_auth_user_id  INTEGER OUTPUT
AS
BEGIN TRY  
	DECLARE @transaction_name VARCHAR(12) = 'transq_001';
  SET @inserted_auth_user_id = -1;
	BEGIN TRANSACTION @transaction_name
	
		INSERT INTO dbo.auth_users (email_address, password_hash, status)
			VALUES 
		(@email, @password_hash, @status);

		SET @inserted_auth_user_id = @@IDENTITY;
	
		INSERT INTO dbo.auth_users_info 
		(auth_user_id, country_code, create_date, first_name, last_name, phone, base64_picture)
			VALUES
		(@inserted_auth_user_id, @country_code, GETDATE(), 
            @first_name, @last_name, @phone, @base64_picture);
		
		INSERT INTO dbo.auth_user_roles (auth_user_id, role_id)
		SELECT @inserted_auth_user_id AS us_id, r.role_id 
		FROM dbo.auth_roles AS r
		WHERE r.role_name 
		IN (
			SELECT value FROM STRING_SPLIT(@roles, @roles_split_separator)
		);
	
	COMMIT TRANSACTION @transaction_name
END TRY  
BEGIN CATCH  
	ROLLBACK TRANSACTION @transaction_name
	EXECUTE dbo.report_error
END CATCH 
