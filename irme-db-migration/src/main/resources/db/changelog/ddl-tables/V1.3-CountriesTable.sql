--liquibase formatted sql
--changeset IvanPostu:ddl-tables-V1.3 splitStatements:true endDelimiter:;

CREATE TABLE [dbo].[countries] (
    [country_code] varchar(2) NOT NULL,
    [country_name] varchar(64) NOT NULL,
    CONSTRAINT [pk_countries] PRIMARY KEY CLUSTERED ([country_code] ASC)
);

--rollback <rollback SQL statements>
--rollback <rollback SQL statements>
