
GO;

  DELETE ou
  FROM [dbo].[organisations_users] ou
  INNER JOIN dbo.organisations AS o
  ON o.organisation_id = ou.organisation_id 
  WHERE o.name IN (
    'organisationTest_01',
    'organisationTest_02',
    'organisationTest_03',
    'organisationTest_04',
    'organisationTest_05'
  );
  

  DELETE FROM dbo.organisations WHERE [name] IN (
    'organisationTest_01',
    'organisationTest_02',
    'organisationTest_03',
    'organisationTest_04',
    'organisationTest_05'
  );

GO;