CREATE   PROCEDURE [dbo].[sa_create_evaluation_process]
    @organisation_id 			INTEGER,
    @author_user_id 			INTEGER,
    @category_id 				INTEGER,
    @inserted_process_id  		INTEGER OUTPUT
AS
BEGIN TRY 
	-- -3categoryNotExists 
	-- -2userDontHavePermisionToOrganisation
	-- -1Genericerror 
	SET @inserted_process_id = -1;
	
	--Check if user is assigned to organisation
	DECLARE @isAsignedToOrganisation BIT = (
		SELECT TOP 1 1 FROM dbo.organisations_users AS ou
		WHERE ou.auth_user_id=@author_user_id AND ou.organisation_id=@organisation_id
	)
	IF @isAsignedToOrganisation <> 1 
	BEGIN 
		SET @inserted_process_id = -2;
		RETURN
	END
	
	--Check if category exists
	IF NOT EXISTS (SELECT TOP 1 1 FROM dbo.sa__categories AS sc WHERE sc.category_id=@category_id)
	BEGIN 
		SET @inserted_process_id = -3;
		RETURN
	END
	
	--status: 0:open, 1:completed, 2:force_closed
	INSERT INTO sa__processes 
		(organisation_id, created, author_user_id, status, category_id)
	VALUES 
		(@organisation_id, GETDATE(), @author_user_id, 0, @category_id);

	SET @inserted_process_id = @@IDENTITY;
	
END TRY  
BEGIN CATCH
	
	EXECUTE dbo.report_error
END CATCH;
