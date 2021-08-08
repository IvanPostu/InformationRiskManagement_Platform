
GO;

  DELETE FROM dbo.organisations WHERE [name] IN (
    'organisationTest_01',
    'organisationTest_02',
    'organisationTest_03',
    'organisationTest_04',
    'organisationTest_05'
  );

GO;
