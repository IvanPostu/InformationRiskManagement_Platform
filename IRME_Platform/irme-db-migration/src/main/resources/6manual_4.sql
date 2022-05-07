
BEGIN

    DECLARE @loopCounter INTEGER = 1; --11
    DECLARE @d DATETIME;

    WHILE @loopCounter <= 11
    BEGIN
      SET @d = DATEADD(DAY, -1, GETDATE());
      EXECUTE [dbo].[dev__evaluate_organisation] 4, @loopCounter, 9, @d;
      SET @d = DATEADD(DAY, -2, GETDATE());
      EXECUTE [dbo].[dev__evaluate_organisation] 4, @loopCounter, 9, @d;
      SET @d = DATEADD(DAY, -3, GETDATE());
      EXECUTE [dbo].[dev__evaluate_organisation] 4, @loopCounter, 9, @d;
      SET @d = DATEADD(DAY, -4, GETDATE());
      EXECUTE [dbo].[dev__evaluate_organisation] 4, @loopCounter, 9, @d;
      SET @d = DATEADD(DAY, -5, GETDATE());
      EXECUTE [dbo].[dev__evaluate_organisation] 4, @loopCounter, 9, @d;
      SET @d = DATEADD(DAY, -6, GETDATE());
      EXECUTE [dbo].[dev__evaluate_organisation] 4, @loopCounter, 9, @d;

      SET @loopCounter = @loopCounter + 1;
    END

END

