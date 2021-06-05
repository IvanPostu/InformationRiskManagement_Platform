package com.irme.server.dal.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class BaseDataAccessObject {

    protected final DataSource dataSource;

    private boolean transactionRunning = false;
    private Connection connection;

    public BaseDataAccessObject(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected Connection getConnection() throws SQLException {
        return connection != null ? connection : dataSource.getConnection();
    }

    public void beginTransaction() throws SQLException {
        if (transactionRunning) {
            throw new RuntimeException("Transaction already running!");
        }

        transactionRunning = true;
        connection = dataSource.getConnection();

        try (Statement statement = connection.createStatement()) {
            statement.execute("BEGIN TRANSACTION t_00010");
        }

    }

    public void commitTransaction() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("COMMIT TRANSACTION t_00010");
        }

    }

    public void rollbackTransactionIfExists() throws SQLException {
        if (!transactionRunning) {
            return;
        }

        try (Statement statement = connection.createStatement()) {
            statement.execute("ROLLBACK TRANSACTION t_00010");
        }

        connection.close();
    }

}
