

GO;

  
  EXECUTE dbo.organisation_save -1, 'ABC Enterprise', 'An organization, or organisation (Commonwealth English; see spelling differences), is an entity – such as a company, an institution, or an association – comprising one or more people and having a particular purpose.', '', @q OUT;
  
  EXECUTE dbo.organisation_save -1, 'QWE Integrator', 'A voluntary association is an organization consisting of volunteers. Such organizations may be able to operate without legal formalities, depending on jurisdiction, including informal clubs or coordinating bodies with a goal in mind which they may express in the form of an manifesto, mission statement, or in an informal manner reflected in what they do because remember every action done by an organization both legal and illegal reflects a goal in mind.', '', @q OUT;
  
  EXECUTE dbo.organisation_save -1, 'IT Solutions', 'Organizations may also operate secretly or illegally in the case of secret societies, criminal organizations and resistance movements. And in some cases may have obstacles from other organizations', '', @q OUT;
  
  EXECUTE dbo.organisation_save -1, 'QWE Enterprise', 'This organizational structure promotes internal competition. Inefficient components of the organization starve, while effective ones get more work. Everybody is paid for what they actually do, and so runs a tiny business that has to show a profit, or they are fired.', '', @q OUT;
  
  EXECUTE dbo.organisation_save -1, 'B2B Solutions', 'This organizational type assigns each worker two bosses in two different hierarchies. One hierarchy is "functional" and assures that each type of expert in the organization is well-trained, and measured by a boss who is super-expert in the same field. The other direction is "executive" and tries to get projects completed using the experts. Projects might be organized by products, regions, customer types, or some other schemes.', '', @q OUT;


GO;
