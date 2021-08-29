GO

  CREATE TRIGGER [dbo].[trg_liquibase_changelog_tag_creator]
  ON [dbo].[liquibase_db_changelog]
  INSTEAD OF INSERT
  AS BEGIN 
    INSERT INTO [dbo].[liquibase_db_changelog]
    (
      ID, 
      AUTHOR, 
      FILENAME, 
      DATEEXECUTED, 
      ORDEREXECUTED, 
      EXECTYPE, 
      MD5SUM, 
      DESCRIPTION, 
      COMMENTS, 
      TAG, 
      LIQUIBASE, 
      CONTEXTS, 
      LABELS,
      DEPLOYMENT_ID 
    )
    SELECT
      i.ID,
      i.AUTHOR,
      i.FILENAME,
      i.DATEEXECUTED,
      i.ORDEREXECUTED,
      i.EXECTYPE,
      i.MD5SUM,
      i.DESCRIPTION,
      i.COMMENTS,
      i.ID,
      i.LIQUIBASE,
      i.CONTEXTS,
      i.LABELS,
      i.DEPLOYMENT_ID 
    FROM inserted AS i;
  END;

GO
