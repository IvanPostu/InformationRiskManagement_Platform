
CREATE OR ALTER PROCEDURE [dbo].[get_countries]
AS
	SELECT 
		countries.country_code, 
		countries.country_name 
	FROM dbo.countries AS countries;

