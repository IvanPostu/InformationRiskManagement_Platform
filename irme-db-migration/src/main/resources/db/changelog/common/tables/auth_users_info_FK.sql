

ALTER TABLE
    [dbo].[auth_users_info]
ADD CONSTRAINT
    fk_auth_user_info_id
FOREIGN KEY ( auth_user_id ) 
REFERENCES [dbo].[auth_users]( auth_user_id )

