
DECLARE @z INTEGER;
EXECUTE dbo.auth_user_with_info_add
	'testUser1@mail.ru', 
	'q', 
	'ACTIVE', 
	'ROLE_ADMIN;ROLE_USER;ROLE_DEV;', 
	';', 
	'Jimmy', 
	'Rick', 
	'068888888', 
	'MD',
  'imgtest01',
  @z OUTPUT;

EXECUTE dbo.auth_user_with_info_add
	'testUser2@mail.ru', 
	'w', 
	'ACTIVE', 
	'ROLE_ADMIN;', 
	';', 
	'Sergiu', 
	'Bomber', 
	'068888888', 
	'MD',
  'imgtest02',
  @z OUTPUT;

EXECUTE dbo.auth_user_with_info_add
	'testUser3@mail.ru', 
	'e', 
	'ACTIVE', 
	'ROLE_USER;', 
	';', 
	'Victor', 
	'Rocker', 
	'068888888', 
	'MD',
  'imgtest03',
  @z OUTPUT;

