

GO;

  DECLARE @q INTEGER;
  EXECUTE dbo.organisation_insert 'ABC Enterprise', 'decrtiption...', '', @q OUT;
  EXECUTE dbo.organisation_insert 'QWE Integrator', 'decrtiption...', '', @q OUT;
  EXECUTE dbo.organisation_insert 'IT Solutions', 'decrtiption...', '', @q OUT;
  EXECUTE dbo.organisation_insert 'QWE Enterprise', 'decrtiption...', '', @q OUT;
  EXECUTE dbo.organisation_insert 'B2B Solutions', 'decrtiption...', '', @q OUT;

GO;
