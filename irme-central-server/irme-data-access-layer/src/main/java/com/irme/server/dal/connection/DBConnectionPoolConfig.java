package com.irme.server.dal.connection;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DBConnectionPoolConfig {
    private final String dbDriver;
    private final String dbUrl;
    private final String dbUsername;
    private final String dbPassword;
    private final Integer maxConnections;
}
