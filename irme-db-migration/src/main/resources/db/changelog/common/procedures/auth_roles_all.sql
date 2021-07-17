
CREATE OR ALTER PROCEDURE [dbo].[auth_user_roles_all]
AS
BEGIN
	SELECT role_id, role_name FROM dbo.auth_roles;
END;
