
 

BEGIN TRANSACTION
	DECLARE @users_ids NVARCHAR(MAX) = '';
	SELECT @users_ids=@users_ids+CONCAT(au.auth_user_id, ',') 
	FROM dbo.auth_users AS au
	WHERE email_address IN (
		'testUser3@mail.ru', 
		'testUser2@mail.ru', 
		'testUser1@mail.ru'
	)
	SET @users_ids = SUBSTRING(@users_ids, 0, LEN(@users_ids)) 
	DECLARE @delete_query NVARCHAR(MAX) = CONCAT(
		'DELETE FROM dbo.auth_user_roles WHERE auth_user_id IN (',
			@users_ids,
		');',
		'DELETE FROM dbo.auth_users_info WHERE auth_user_id IN (',
			@users_ids,
		')',
		'DELETE FROM dbo.auth_users WHERE auth_user_id IN (',
			@users_ids,
		')'
	);
	
	EXECUTE (@delete_query);
COMMIT TRANSACTION
