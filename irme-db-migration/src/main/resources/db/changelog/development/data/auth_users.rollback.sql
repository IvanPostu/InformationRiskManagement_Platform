
BEGIN TRANSACTION
	DECLARE @users_ids NVARCHAR(MAX) = '';
	SELECT @users_ids=@users_ids+CONCAT(au.auth_user_id, ',') 
	FROM dbo.auth_users AS au
	WHERE email_address IN (
		'q@mail.ru', 
		'w@mail.ru', 
		'e@mail.ru', 
		'r@mail.ru', 
		'q1@mail.ru', 
		'w1@mail.ru', 
		'e1@mail.ru', 
		'r1@mail.ru',
    'admin@mail.ru'
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
	
	IF LEN(@users_ids) > 0
	BEGIN
		EXECUTE sp_executesql @delete_query;
	END
		
COMMIT TRANSACTION


