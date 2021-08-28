
DELETE FROM dbo.auth_user_roles 
WHERE role_id IN (
	SELECT TOP 1 role_id 
	FROM dbo.auth_roles 
	WHERE role_name='ROLE_DEV'
);


DELETE FROM auth_roles WHERE role_id IN (636);



