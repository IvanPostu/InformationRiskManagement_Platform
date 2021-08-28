

GO;

  DECLARE @q INTEGER;
  EXECUTE dbo.organisation_save -1, 'organisationTest_01', 'decrtiption...', '', @q OUT;
  EXECUTE dbo.organisation_save -1, 'organisationTest_02', 'decrtiption...', '', @q OUT;
  EXECUTE dbo.organisation_save -1, 'organisationTest_03', 'decrtiption...', '', @q OUT;
  EXECUTE dbo.organisation_save -1, 'organisationTest_04', 'decrtiption...', '', @q OUT;
  EXECUTE dbo.organisation_save -1, 'organisationTest_05', 'decrtiption...', '', @q OUT;

GO;
