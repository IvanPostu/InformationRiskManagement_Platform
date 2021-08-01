-- Run on change TRUE

GO;
  IF (OBJECT_ID('dbo.fk_auth_users__organisations_users', 'F') IS NOT NULL)
  BEGIN
    ALTER TABLE
        [dbo].[organisations_users]
    DROP CONSTRAINT
        fk_auth_users__organisations_users;
  END;

  IF (OBJECT_ID('dbo.fk_organisations__organisations_users', 'F') IS NOT NULL)
  BEGIN
    ALTER TABLE
        [dbo].[organisations_users]
    DROP CONSTRAINT
        fk_organisations__organisations_users;
  END;
GO;
  ALTER TABLE
      [dbo].[organisations_users]
  ADD CONSTRAINT
      fk_auth_users__organisations_users
  FOREIGN KEY ([auth_user_id]) 
  REFERENCES [dbo].[auth_users]([auth_user_id]);

  ALTER TABLE
      [dbo].[organisations_users]
  ADD CONSTRAINT
      fk_organisations__organisations_users
  FOREIGN KEY ([organisation_id]) 
  REFERENCES [dbo].[organisations]([organisation_id]);
GO;
