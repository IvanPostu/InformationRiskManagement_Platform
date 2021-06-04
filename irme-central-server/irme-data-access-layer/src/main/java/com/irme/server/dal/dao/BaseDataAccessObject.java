package com.irme.server.dal.dao;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

public abstract class BaseDataAccessObject {

    private final DataSource dataSource;

    public BaseDataAccessObject(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected Connection getConnection() throws SQLException{
        return this.dataSource.getConnection();
    }

}
