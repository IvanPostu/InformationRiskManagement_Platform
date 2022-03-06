
CREATE PROCEDURE [dbo].[organisation_save]
  @organisationId             INTEGER,
	@name  				              NVARCHAR(256),
  @description  		    	    NVARCHAR(512),
  @base64_picture  			      NVARCHAR(MAX),
  @inserted_organisation_id  	INTEGER OUTPUT
AS
BEGIN TRY
	DECLARE @transaction_name NVARCHAR(12) = 'transq_001';
	SET @inserted_organisation_id = -1;

	BEGIN TRANSACTION @transaction_name;
	
    IF @organisationId > 0
    BEGIN
      UPDATE dbo.organisations 
      SET 
        name = @name , 
        description = @description, 
        base64_logo=@base64_picture, 
        status='ACTIVE'
      WHERE organisation_id = @organisationId;

      SET @inserted_organisation_id = 1;
    END
    ELSE
    BEGIN 
      INSERT INTO [dbo].[organisations] 
        (name, description, base64_logo, created, status)
      VALUES
        (@name, @description, @base64_picture, GETDATE(), 'ACTIVE');
      
      SET @inserted_organisation_id = @@IDENTITY;
    END
	
	COMMIT TRANSACTION @transaction_name;
END TRY
BEGIN CATCH
	ROLLBACK TRANSACTION @transaction_name;
	EXECUTE dbo.report_error;
END CATCH 

