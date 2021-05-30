--liquibase formatted sql
--changeset IvanPostu:ddl-procedures-V1.4 splitStatements:true endDelimiter:GO context:default

CREATE OR ALTER PROCEDURE [dbo].[auth_user_by_email]
    @email VARCHAR(256)
AS
BEGIN TRY  
	SELECT TOP 1
		au.email_address, 
		au.password_hash, 
		au.status,
		aui.first_name , 
		aui.last_name, 
		aui.create_date,
		aui.phone,
		aui.country_code,
		(	
			SELECT CAST(ar.role_name + ';' AS VARCHAR(MAX)) 
			FROM dbo.auth_roles AS ar
			WHERE ar.role_id IN (
				SELECT aur.role_id FROM dbo.auth_user_roles AS aur
				WHERE aur.auth_user_id = au.auth_user_id)
			FOR XML PATH ('')
		) AS roles
	FROM dbo.auth_users AS au 
	INNER JOIN dbo.auth_users_info AS aui 
	ON aui.auth_user_id = au.auth_user_id
	WHERE au.email_address=@email;
END TRY  
BEGIN CATCH
	EXECUTE dbo.report_error
END CATCH;

--rollback <rollback SQL statements>
--rollback <rollback SQL statements>
