GO;
  DELETE FROM auth_user_roles WHERE role_id IN (1, 2);
  
  DELETE FROM auth_roles WHERE role_id IN (1, 2);
GO;
