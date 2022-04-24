
CREATE OR ALTER PROCEDURE [dbo].[sa_finish_evaluation]
    @process_id				INTEGER,
    @organisation_id 		INTEGER,
    @force_close			BIT=0,
    @isSuccess  			BIT OUTPUT
AS
BEGIN TRY 
	SET @isSuccess = 0;
	DECLARE @error_message NVARCHAR(1024) = '';	
	DECLARE @process_exists BIT = 0;
	
	SET @process_exists = (
		SELECT TOP 1 1 FROM sa__processes AS sp 
		WHERE sp.process_id=@process_id	AND sp.organisation_id=@organisation_id
	) 

	IF @process_exists=1
	BEGIN 
		UPDATE sp 
		SET sp.status=1
		FROM sa__processes AS sp
		WHERE sp.process_id=@process_id	AND sp.organisation_id=@organisation_id;
	END
	ELSE 
	BEGIN 
		SET @error_message = CONCAT('Not found evaluation process with id: ', 
	        @process_id	,
	        ' for organisation with id: ',
	        @organisation_id
	    );
	    RAISERROR(@error_message, 16, 1)
	END

	SET @isSuccess=1;
END TRY  
BEGIN CATCH
	EXECUTE dbo.report_error;
END CATCH;

