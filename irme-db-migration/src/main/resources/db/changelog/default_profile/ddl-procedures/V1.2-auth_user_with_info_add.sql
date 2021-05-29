--liquibase formatted sql
--changeset IvanPostu:ddl-procedures-V1.2 splitStatements:true endDelimiter:GO context:default

CREATE OR ALTER PROCEDURE [dbo].[auth_user_with_info_add]
    @email  				VARCHAR(128),
    @password_hash  		VARCHAR(256),
    @status  				VARCHAR(32) = 'ACTIVE',
    @roles					VARCHAR(512) = 'ROLE_USER',
    @roles_split_separator	CHAR(1) = ';',
    @first_name  			VARCHAR(64),
    @last_name  			VARCHAR(64),
    @phone  				VARCHAR(30),
    @country_code  			VARCHAR(2)
AS
BEGIN TRY  
	DECLARE @transaction_name VARCHAR(12) = 'transq_001' 
	DECLARE @inserted_auth_user_id INTEGER = -1
	BEGIN TRANSACTION @transaction_name
	
		INSERT INTO dbo.auth_users (email_address, password_hash, status)
			VALUES 
		(@email, @password_hash, @status);

		SET @inserted_auth_user_id = @@IDENTITY;
	
		INSERT INTO dbo.auth_users_info 
		(auth_user_id, country_code, create_date, first_name, last_name, phone)
			VALUES
		(@inserted_auth_user_id, @country_code, GETDATE(), 
            @first_name, @last_name, @phone);
		
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


--rollback <rollback SQL statements>
--rollback <rollback SQL statements>
