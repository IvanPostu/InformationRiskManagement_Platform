package com.irme.server.dal.dao;

import java.sql.Connection;

public abstract class BaseDataAccessObject {

    private final Connection connection;

    public BaseDataAccessObject(Connection connection) {
        this.connection = connection;
    }

    protected Connection getConnection(){
        return this.connection;
    }

}
