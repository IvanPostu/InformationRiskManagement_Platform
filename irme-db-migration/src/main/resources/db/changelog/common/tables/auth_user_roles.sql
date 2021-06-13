
CREATE TABLE [dbo].[auth_user_roles] (
    [auth_user_id]  INTEGER NOT NULL,
    [role_id]       INTEGER NOT NULL,

    CONSTRAINT [pk_auth_user_roles] 
    PRIMARY KEY 
    CLUSTERED ([auth_user_id], [role_id])
);
