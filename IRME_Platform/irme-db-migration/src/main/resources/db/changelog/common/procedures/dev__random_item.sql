
CREATE OR ALTER PROCEDURE [dbo].[dev__random_item]
    @set_with_comma_separated			NVARCHAR(MAX),
    @result_item 						INTEGER OUTPUT
AS
BEGIN TRY 
	DECLARE @items AS TABLE(id INTEGER);
	DECLARE @total INTEGER = 0;
	DECLARE @current_index INTEGER;

	INSERT INTO @items (id)
	SELECT value FROM STRING_SPLIT(@set_with_comma_separated, ',');
	
	SET @total = (SELECT COUNT(id) FROM @items);
	SET @current_index = ( SELECT ABS(CHECKSUM(NEWID()) % @total) );
	SET @result_item = (
		SELECT id FROM @items
		ORDER BY id ASC 
		OFFSET @current_index ROWS
		FETCH NEXT 1 ROWS ONLY
	);
END TRY  
BEGIN CATCH
	EXECUTE dbo.report_error;
END CATCH;
