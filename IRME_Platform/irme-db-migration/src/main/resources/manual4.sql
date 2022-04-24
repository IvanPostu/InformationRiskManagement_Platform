
BEGIN

    DECLARE @d DATETIME;

    SET @d = DATEADD(DAY, -1, GETDATE());
    EXECUTE [dbo].[dev__evaluate_organisation] 4, 1, 9, @d;
    SET @d = DATEADD(DAY, -2, GETDATE());
    EXECUTE [dbo].[dev__evaluate_organisation] 4, 1, 9, @d;
    SET @d = DATEADD(DAY, -3, GETDATE());
    EXECUTE [dbo].[dev__evaluate_organisation] 4, 1, 9, @d;
    SET @d = DATEADD(DAY, -4, GETDATE());
    EXECUTE [dbo].[dev__evaluate_organisation] 4, 1, 9, @d;
    SET @d = DATEADD(DAY, -5, GETDATE());
    EXECUTE [dbo].[dev__evaluate_organisation] 4, 1, 9, @d;
    SET @d = DATEADD(DAY, -6, GETDATE());
    EXECUTE [dbo].[dev__evaluate_organisation] 4, 1, 9, @d;


    SET @d = DATEADD(DAY, -1, GETDATE());
    EXECUTE [dbo].[dev__evaluate_organisation] 4, 2, 9, @d;
    SET @d = DATEADD(DAY, -2, GETDATE());
    EXECUTE [dbo].[dev__evaluate_organisation] 4, 2, 9, @d;
    SET @d = DATEADD(DAY, -3, GETDATE());
    EXECUTE [dbo].[dev__evaluate_organisation] 4, 2, 9, @d;
    SET @d = DATEADD(DAY, -4, GETDATE());
    EXECUTE [dbo].[dev__evaluate_organisation] 4, 2, 9, @d;
    SET @d = DATEADD(DAY, -5, GETDATE());
    EXECUTE [dbo].[dev__evaluate_organisation] 4, 2, 9, @d;
    SET @d = DATEADD(DAY, -6, GETDATE());
    EXECUTE [dbo].[dev__evaluate_organisation] 4, 2, 9, @d;

END

