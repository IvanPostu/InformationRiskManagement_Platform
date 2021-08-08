

GO;

  DECLARE @q INTEGER;
  EXECUTE dbo.organisation_insert 'organisationTest_01', 'decrtiption...', '', @q OUT;
  EXECUTE dbo.organisation_insert 'organisationTest_02', 'decrtiption...', '', @q OUT;
  EXECUTE dbo.organisation_insert 'organisationTest_03', 'decrtiption...', '', @q OUT;
  EXECUTE dbo.organisation_insert 'organisationTest_04', 'decrtiption...', '', @q OUT;
  EXECUTE dbo.organisation_insert 'organisationTest_05', 'decrtiption...', '', @q OUT;

GO;
