
DECLARE @z INTEGER;
EXECUTE dbo.auth_user_with_info_add
	'q@mail.ru', 
	'q', 
	'ACTIVE', 
	'ROLE_ADMIN;ROLE_USER;ROLE_DEV;', 
	';', 
	'Jimmy', 
	'Rick', 
	'068888888', 
	'MD',
  'IMG01',
  @z OUTPUT;

EXECUTE dbo.auth_user_with_info_add
	'w@mail.ru', 
	'w', 
	'ACTIVE', 
	'ROLE_ADMIN;', 
	';', 
	'Sergiu', 
	'Bomber', 
	'068888888', 
	'MD',
  'IMG02',
  @z OUTPUT;

EXECUTE dbo.auth_user_with_info_add
	'e@mail.ru', 
	'e', 
	'ACTIVE', 
	'ROLE_USER;', 
	';', 
	'Victor', 
	'Rocker', 
	'068888888', 
	'MD',
  'IMG03',
  @z OUTPUT;

EXECUTE dbo.auth_user_with_info_add
	'r@mail.ru', 
	'r', 
	'ACTIVE', 
	'ROLE_DEV', 
	';', 
	'Misha', 
	'Apple', 
	'068888888', 
	'MD',
  'IMG04',
  @z OUTPUT;
EXECUTE dbo.auth_user_with_info_add
	'q1@mail.ru', 
	'q', 
	'ACTIVE', 
	'ROLE_ADMIN;ROLE_USER;ROLE_DEV;', 
	';', 
	'Jimmy', 
	'Rick', 
	'068888888', 
	'MD',
  'IMG01',
  @z OUTPUT;

EXECUTE dbo.auth_user_with_info_add
	'w1@mail.ru', 
	'w', 
	'ACTIVE', 
	'ROLE_ADMIN;', 
	';', 
	'Sergiu', 
	'Bomber', 
	'068888888', 
	'MD',
  'IMG02',
  @z OUTPUT;

EXECUTE dbo.auth_user_with_info_add
	'e1@mail.ru', 
	'e', 
	'ACTIVE', 
	'ROLE_USER;', 
	';', 
	'Victor', 
	'Rocker', 
	'068888888', 
	'MD',
  'IMG03',
  @z OUTPUT;

EXECUTE dbo.auth_user_with_info_add
	'r1@mail.ru', 
	'r', 
	'ACTIVE', 
	'ROLE_DEV', 
	';', 
	'Misha', 
	'Apple', 
	'068888888', 
	'MD',
  'IMG04',
  @z OUTPUT;

EXECUTE dbo.auth_user_with_info_add
	'admin@mail.ru', 
	'123456', 
	'ACTIVE', 
	'ROLE_ADMIN;ROLE_USER;ROLE_DEV;', 
	';', 
	'Ivan', 
	'Postu', 
	'068888888', 
	'MD',
    'IMG04',
  @z OUTPUT;
