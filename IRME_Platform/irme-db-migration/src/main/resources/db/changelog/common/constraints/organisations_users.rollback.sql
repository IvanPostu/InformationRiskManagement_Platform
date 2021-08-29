

GO;
  ALTER TABLE
      [dbo].[organisations_users]
  DROP CONSTRAINT
      fk_auth_users__organisations_users;

  ALTER TABLE
      [dbo].[organisations_users]
  DROP CONSTRAINT
      fk_organisations__organisations_users;
GO;

