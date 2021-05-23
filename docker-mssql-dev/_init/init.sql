USE [master]

--------------------------------------------------------------------------------
-- Create Database - InformationRiskManagementDatabase  ------------------------
--------------------------------------------------------------------------------
CREATE DATABASE [InformationRiskManagementDatabase] ON  PRIMARY 
(	NAME = N'InformationRiskManagementDatabase', 
	FILENAME = N'/home/mssql/database/InformationRiskManagementDatabase_Data.mdf' , 
	SIZE = 167872KB , MAXSIZE = UNLIMITED, FILEGROWTH = 16384KB 
)
LOG ON 
( 	NAME = N'InformationRiskManagementDatabase_Log', 
	FILENAME = N'/home/mssql/database/InformationRiskManagementDatabase_Log.ldf' , 
	SIZE = 2048KB , MAXSIZE = 4GB , FILEGROWTH = 16384KB 
)
GO

--------------------------------------------------------------------------------
-- Create Database - InformationRiskManagementDatabase for integration tests ---
--------------------------------------------------------------------------------
CREATE DATABASE [InformationRiskManagementDatabase_Test] ON  PRIMARY 
(	NAME = N'InformationRiskManagementDatabase_Test', 
	FILENAME = N'/home/mssql/database/InformationRiskManagementDatabase_Test_Data.mdf' , 
	SIZE = 167872KB , MAXSIZE = UNLIMITED, FILEGROWTH = 16384KB 
)
LOG ON 
( 	NAME = N'InformationRiskManagementDatabase_Test_Log', 
	FILENAME = N'/home/mssql/database/InformationRiskManagementDatabase_Test_Log.ldf' , 
	SIZE = 2048KB , MAXSIZE = 4GB , FILEGROWTH = 16384KB 
)
GO

---------------------------------------------------------------------------
-- Create Users Logins ----------------------------------------------------
---------------------------------------------------------------------------
CREATE LOGIN ApplicationUserLogin WITH PASSWORD = 'qwYertyZ_654321' 
GO
CREATE LOGIN MigrationUserLogin WITH PASSWORD = 'qwYertyZ_654321' 
GO

-- Migration user
USE [InformationRiskManagementDatabase]
CREATE USER jim FOR LOGIN MigrationUserLogin 
GO
GRANT ALL PRIVILEGES 
ON DATABASE::InformationRiskManagementDatabase TO jim 
GO
GRANT ALTER ON SCHEMA::dbo TO [jim] 
GO

USE [InformationRiskManagementDatabase_Test]
CREATE USER jim FOR LOGIN MigrationUserLogin 
GO
GRANT ALL PRIVILEGES 
ON DATABASE::InformationRiskManagementDatabase_Test TO jim 
GO
GRANT ALTER ON SCHEMA::dbo TO [jim] 
GO

-- Application user with CRUD priveleges
USE [InformationRiskManagementDatabase]
CREATE USER bob FOR LOGIN ApplicationUserLogin 
GO
GRANT INSERT, UPDATE, DELETE, SELECT 
ON DATABASE :: InformationRiskManagementDatabase TO bob 
GO

USE [InformationRiskManagementDatabase]
CREATE USER bob FOR LOGIN ApplicationUserLogin 
GO
GRANT INSERT, UPDATE, DELETE, SELECT 
ON DATABASE :: InformationRiskManagementDatabase_Test TO bob 
GO



