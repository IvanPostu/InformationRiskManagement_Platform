USE [master]

---------------------------------------------------------------------------
-- Create Database - InformationRiskManagementDatabase  -------------------
---------------------------------------------------------------------------
CREATE DATABASE [InformationRiskManagementDatabase] ON  PRIMARY 
(	NAME = N'InformationRiskManagementDatabase', 
	FILENAME = N'/home/mssql/database/InformationRiskManagementDatabase_Data.mdf' , 
	SIZE = 167872KB , MAXSIZE = UNLIMITED, FILEGROWTH = 16384KB 
)
LOG ON 
( 	NAME = N'InformationRiskManagementDatabase_Log', 
	FILENAME = N'/home/mssql/database/InformationRiskManagementDatabase_Log.ldf' , 
	SIZE = 2048KB , MAXSIZE = 2048GB , FILEGROWTH = 16384KB 
)
GO

---------------------------------------------------------------------------
-- Create Users Logins ----------------------------------------------------
---------------------------------------------------------------------------
CREATE LOGIN ApplicationUserLogin WITH PASSWORD = 'qwYertyZ_654321' 
GO
CREATE LOGIN MigrationUserLogin WITH PASSWORD = 'qwYertyZ_654321' 
GO
USE [InformationRiskManagementDatabase]

-- Migration user
CREATE USER jim FOR LOGIN MigrationUserLogin 
GO

GRANT ALL PRIVILEGES 
ON DATABASE::InformationRiskManagementDatabase TO jim 
GO
GRANT ALTER ON SCHEMA::dbo TO [jim] 
GO

-- Application user with CRUD priveleges
CREATE USER bob FOR LOGIN ApplicationUserLogin 
GO

GRANT INSERT, UPDATE, DELETE, SELECT 
ON DATABASE :: InformationRiskManagementDatabase TO bob 
GO




