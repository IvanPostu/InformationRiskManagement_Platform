
CREATE OR ALTER PROCEDURE [dbo].[organisation_get_by_id]
	@organisationId INTEGER
AS
BEGIN 
	SELECT TOP 1
		o.organisation_id,
		o.name,
		o.description,
		o.base64_logo,
		o.created,
		o.status
	FROM dbo.organisations AS o
	WITH (NOLOCK)
	WHERE o.organisation_id = @organisationId;
END

