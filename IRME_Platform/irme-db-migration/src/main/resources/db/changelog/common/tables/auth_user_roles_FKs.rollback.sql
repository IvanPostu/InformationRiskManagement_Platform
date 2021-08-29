ALTER TABLE
    [dbo].[auth_user_roles]
DROP CONSTRAINT
    fk_auth_user_id;

ALTER TABLE
    [dbo].[auth_user_roles]
DROP CONSTRAINT
    fk_role_id;
