
CREATE OR ALTER PROCEDURE [dbo].[sa_get_evaluation_processes]
    @author_user_id 			INTEGER = -1,
    @organisation_id 			INTEGER = -1
AS
BEGIN TRY 
	
	DECLARE @user_email NVARCHAR(128) = (
		SELECT au.email_address FROM auth_users au WHERE au.auth_user_id=@author_user_id
	)

	DECLARE @result_sql NVARCHAR(MAX) = CONCAT('
		SELECT 
			e.process_id, 
			e.organisation_id,
			o.name AS organisation_name,
			e.created, 
			e.author_user_id,
			@user_email AS user_email,
			e.status, 
			e.category_id,
			category.name  AS category_name
		FROM sa__processes AS e
		INNER JOIN organisations AS o ON o.organisation_id=e.organisation_id 
		INNER JOIN sa__categories AS category ON category.category_id=e.category_id 
		WHERE 1=1 ', 
			IIF(@author_user_id<>-1, ' AND e.author_user_id=@author_user_id ', ''), 
			IIF(@organisation_id<>-1, ' AND e.organisation_id=@organisation_id ', ''), 
		' ORDER BY e.created DESC
	');

	EXECUTE sp_executesql @result_sql,
		N'@author_user_id INTEGER, @organisation_id INTEGER, @user_email NVARCHAR(128)',
		@author_user_id, @organisation_id, @user_email;
	
END TRY  
BEGIN CATCH
	EXECUTE dbo.report_error
END CATCH;
