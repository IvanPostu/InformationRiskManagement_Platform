

BEGIN TRAN
	DECLARE @dropProceduresQuery NVARCHAR(MAX) = 'DROP PROCEDURE ';
	
	SELECT 
		@dropProceduresQuery = @dropProceduresQuery + '[dbo].[' + s.name + '],'
	FROM dbo.sysobjects AS s
	WHERE (s.type = 'P') AND s.crdate > '2021-08-08 00:00:02';
	   
	SET @dropProceduresQuery = SUBSTRING(@dropProceduresQuery, 0, LEN(@dropProceduresQuery));
	
	EXECUTE sp_executesql @dropProceduresQuery;
COMMIT TRAN
   