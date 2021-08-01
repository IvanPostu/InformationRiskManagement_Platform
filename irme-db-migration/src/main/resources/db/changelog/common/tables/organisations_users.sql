
CREATE TABLE [dbo].[organisations_users] (
    [auth_user_id]  		INTEGER NOT NULL,
    [organisation_id]   INTEGER NOT NULL,

    CONSTRAINT [pk_organisations_users] 
    PRIMARY KEY 
    CLUSTERED ([auth_user_id], [organisation_id])
);

