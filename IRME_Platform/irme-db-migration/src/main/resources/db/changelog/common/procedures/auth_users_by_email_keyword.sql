

CREATE PROCEDURE [dbo].[auth_users_by_email_keyword]
    @email_keyword NVARCHAR(256),
    @rows_max_count INTEGER
AS
BEGIN TRY  
	
	DECLARE @result_query NVARCHAR(MAX) = CONCAT('
		SELECT TOP ', @rows_max_count,'
	    au.auth_user_id,
			au.email_address, 
			au.password_hash, 
			au.status,
			aui.first_name , 
			aui.last_name, 
			aui.create_date,
			aui.phone,
			aui.country_code,
	    	aui.base64_picture,
			(	
				SELECT CAST(ar.role_name + '';'' AS VARCHAR(MAX)) 
				FROM dbo.auth_roles AS ar
				WHERE ar.role_id IN (
					SELECT aur.role_id FROM dbo.auth_user_roles AS aur
					WHERE aur.auth_user_id = au.auth_user_id)
				FOR XML PATH ('''')
			) AS roles
		FROM dbo.auth_users AS au 
		INNER JOIN dbo.auth_users_info AS aui 
		ON aui.auth_user_id = au.auth_user_id
		WHERE au.email_address LIKE ''%',@email_keyword ,'%'';
		'
	);

	EXECUTE sp_executesql @result_query;
END TRY  
BEGIN CATCH
	EXECUTE dbo.report_error
END CATCH;

