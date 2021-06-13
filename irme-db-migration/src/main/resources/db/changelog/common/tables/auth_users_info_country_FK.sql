

ALTER TABLE
    [dbo].[auth_users_info]
ADD CONSTRAINT
    fk_country_code
FOREIGN KEY ([country_code]) 
REFERENCES [dbo].[countries]([country_code])
