package com.irme.server.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App {
    public static String message() {
        return "D2";
    }

    public static void main(String[] args) throws SQLException {
        String dbURL =
                "jdbc:sqlserver://0.0.0.0:1433;databaseName=InformationRiskManagementDatabase;user=sa;password=Testing1122990";
        Connection conn = DriverManager.getConnection(dbURL);
        if (conn != null) {
            System.out.println("Connected");
        }
    }
}
