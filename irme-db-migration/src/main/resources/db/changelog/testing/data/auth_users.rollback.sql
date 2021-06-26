
 

DECLARE @_offset INTEGER = 0;
WHILE EXISTS(
	SELECT 1 FROM dbo.auth_users AS au
	WHERE email_address IN ('testUser3@mail.ru', 
		'testUser2@mail.ru', 'testUser1@mail.ru')
	ORDER BY auth_user_id 
	OFFSET @_offset ROWS FETCH NEXT 1 ROWS ONLY
) 
BEGIN
	
    DECLARE @_auth_user_id INTEGER = (
			SELECT auth_user_id FROM dbo.auth_users AS au
			WHERE email_address IN ('testUser3@mail.ru', 
				'testUser2@mail.ru', 'testUser1@mail.ru')
			ORDER BY auth_user_id 
			OFFSET @_offset ROWS FETCH NEXT 1 ROWS ONLY
    );
	

    EXEC [dbo].[auth_user_with_info_delete] @_auth_user_id;
	SET @_offset = @_offset + 1;
END;

