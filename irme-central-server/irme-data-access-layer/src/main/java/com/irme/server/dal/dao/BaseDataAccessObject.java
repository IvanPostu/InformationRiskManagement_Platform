package com.irme.server.dal.dao;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
public abstract class BaseDataAccessObject implements AutoCloseable {

    protected final DataSource dataSource;
    @Getter
    private boolean transactionRunning;

    @Getter
    private boolean open;

    private Connection connection;

    protected BaseDataAccessObject(DataSource dataSource) throws Exception {
        this.dataSource = dataSource;
        this.transactionRunning = false;
        this.open = false;

        this.open();
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
            log.warn("There are currently active transaction");
            return;
        }
        transactionRunning = true;


        try (Statement statement = connection.createStatement()) {
            statement.execute("BEGIN TRANSACTION t_00010");
        }

    }

    public void commitTransaction() throws SQLException {
        if (!transactionRunning) {
            log.warn("There are currently no active transaction");
            return;
        }

        try (Statement statement = connection.createStatement()) {
            statement.execute("COMMIT TRANSACTION t_00010");
        }
        transactionRunning = false;

    }

    public void rollbackTransactionIfExists() throws SQLException {
        if (!transactionRunning) {
            log.warn("There are currently no active transactions");
            return;
        }

        try (Statement statement = connection.createStatement()) {
            statement.execute("ROLLBACK TRANSACTION t_00010");
        }
        transactionRunning = false;

    }

    public void open() throws Exception {
        if (isOpen()) {
            throw new RuntimeException("Data access object already is open");
        }

        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new Exception(e);
        }
        this.open = true;
    }

    @Override
    public void close() throws Exception {
        if (!isOpen()) {
            throw new RuntimeException("Data access object already is closed");
        }

        try {
            if (isTransactionRunning()) {
                rollbackTransactionIfExists();
            }
            this.open = false;

            this.connection.close();
            this.connection = null;

        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

}
