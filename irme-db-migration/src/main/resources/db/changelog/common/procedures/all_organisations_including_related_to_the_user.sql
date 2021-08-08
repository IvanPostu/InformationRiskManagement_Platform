

CREATE PROCEDURE [dbo].[all_organisations_including_related_to_the_user]
    @curent_user_id		INTEGER
AS
BEGIN TRY  

	SELECT 
		org.organisation_id,
		org.name,
		org.description,
		org.base64_logo ,
		org.created,
		org.status,
		(IIF(org_users.auth_user_id=@curent_user_id, 1, 0)) AS refers_to_organisation
	FROM dbo.organisations AS org
	LEFT JOIN dbo.organisations_users 
	AS 
		org_users ON org_users.organisation_id = org.organisation_id AND
		org_users.auth_user_id = @curent_user_id;

END TRY  
BEGIN CATCH
	EXECUTE dbo.report_error;
END CATCH;


