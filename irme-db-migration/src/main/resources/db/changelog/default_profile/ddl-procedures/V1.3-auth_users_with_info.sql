--liquibase formatted sql
--changeset IvanPostu:ddl-procedures-V1.3 splitStatements:true endDelimiter:GO context:default

CREATE OR ALTER PROCEDURE [dbo].[auth_users_with_info]
    @offset_  				INTEGER,
    @limit_			  		INTEGER,
    @role_split_separator   CHAR(1) = ';',
    @sort_asc				BIT = 1
AS
BEGIN TRY  

	SELECT 
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
	ORDER BY 
		CASE WHEN @sort_asc=1 THEN au.auth_user_id ELSE -au.auth_user_id END ASC
	OFFSET @offset_ ROWS
	FETCH NEXT @limit_ ROWS ONLY;

END TRY  
BEGIN CATCH
	EXECUTE dbo.report_error
END CATCH;


--rollback <rollback SQL statements>
--rollback <rollback SQL statements>
