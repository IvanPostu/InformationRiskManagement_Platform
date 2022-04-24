


DECLARE @adminUserId INTEGER = (
    SELECT TOP 1 au.auth_user_id FROM auth_users AS au 
    WHERE au.email_address='admin@mail.ru'
)

INSERT INTO dbo.organisations_users (auth_user_id, organisation_id)
SELECT TOP 3 
	@adminUserId,
	o.organisation_id
FROM organisations AS o ;

