package com.irme.server.dal.dao;

import lombok.Getter;
import javax.sql.DataSource;
import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class BaseDataAccessObject implements Closeable {

    protected final DataSource dataSource;
    @Getter
    private boolean transactionRunning;
    private Connection connection;

    protected BaseDataAccessObject(DataSource dataSource) {
        this.dataSource = dataSource;
        this.transactionRunning = false;
    }

    protected Connection getConnection() throws SQLException {
        if (connection != null) {
            return connection;
        }

        if (!transactionRunning) {
            return dataSource.getConnection();
        }

        throw new RuntimeException("Transaction running but connection is null!!!");
    }

    public void beginTransaction() throws SQLException {
        if (transactionRunning) {
            throw new RuntimeException("Transaction already running!");
        }
        transactionRunning = true;

        if (connection != null && !connection.isClosed()) {
            connection.close();
        }

        connection = dataSource.getConnection();

        try (Statement statement = connection.createStatement()) {
            statement.execute("BEGIN TRANSACTION t_00010");
        }

    }

    public void commitTransaction() throws SQLException {
        if (!transactionRunning) {
            return;
        }

        try (Statement statement = connection.createStatement()) {
            statement.execute("COMMIT TRANSACTION t_00010");
        }

        transactionRunning = false;

    }

    public void rollbackTransactionIfExists() throws SQLException {
        if (!transactionRunning) {
            return;
        }

        try (Statement statement = connection.createStatement()) {
            statement.execute("ROLLBACK TRANSACTION t_00010");
        }

        transactionRunning = false;
    }

    @Override
    public void close() throws IOException {
        try {
            if (isTransactionRunning()) {
                rollbackTransactionIfExists();
            }

            connection.close();
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }

}
