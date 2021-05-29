package com.irme.server.dal;

import java.sql.Connection;

public abstract class BaseDataAccessObject {

    protected final Connection connection;

    public BaseDataAccessObject(Connection connection) {
        this.connection = connection;
    }

}
