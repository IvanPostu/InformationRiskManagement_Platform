CREATE TABLE [dbo].[countries] (
    [country_code] CHAR(2) NOT NULL,
    [country_name] NVARCHAR(64) NOT NULL,
    CONSTRAINT [pk_countries] PRIMARY KEY CLUSTERED ([country_code] ASC)
);