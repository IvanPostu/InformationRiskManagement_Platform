CREATE   PROCEDURE [dbo].[sa_get_evaluation_processes]
    @author_user_id 			INTEGER = -1,
    @organisation_id 			INTEGER = -1
AS
BEGIN TRY 
	
	DECLARE @user_email NVARCHAR(128) = (
		SELECT au.email_address FROM auth_users au WHERE au.auth_user_id=@author_user_id
	)

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
	WHERE e.author_user_id=@author_user_id OR e.organisation_id=@organisation_id
	ORDER BY e.created DESC;
	
END TRY  
BEGIN CATCH
	EXECUTE dbo.report_error
END CATCH;
