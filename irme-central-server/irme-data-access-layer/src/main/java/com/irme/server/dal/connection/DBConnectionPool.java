package com.irme.server.dal.connection;

import java.sql.Connection;
import javax.sql.DataSource;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DBConnectionPool {

    private static final Logger logger = LoggerFactory.getLogger(DBConnectionPool.class);
    private static GenericObjectPool<Connection> gPool = null;
    private static DBConnectionPoolConfig _dbConnectionPoolConfig;
    private static final Object initializeLockObject = new Object();
    private static final Object singletonLockObject = new Object();
    private static boolean isInitialized = false;
    private static DataSource poolSingleton;

    private DBConnectionPool() {}

    public static DataSource getPool(){
        synchronized(singletonLockObject){
            if(poolSingleton == null){
                try {
                    poolSingleton = setUpPool();
                } catch (Exception e) {
                    logger.error(e.getMessage());
                    throw new Error(e);
                }
            }
            return poolSingleton;
        }
    }

    public static void initialize(DBConnectionPoolConfig dbConnectionPoolConfig) {
        synchronized (initializeLockObject) {
            if(isInitialized){
                logger.error("The connection pool has already initialized !!!");
                throw new Error();
            }

            _dbConnectionPoolConfig = dbConnectionPoolConfig;

            isInitialized = true;
        }
    }

    public static String poolStatus() {
        return new StringBuilder()
                .append("Max.: ")
                .append(gPool.getMaxActive())
                .append("; Active: ")
                .append(gPool.getNumActive())
                .append("; Idle: ")
                .append(gPool.getNumIdle())
                .toString();
    }

    @SuppressWarnings("unused")
    private static DataSource setUpPool() throws Exception {
        Class.forName(_dbConnectionPoolConfig.getDbDriver());

        /**
         * Creates an Instance of GenericObjectPool That Holds  Pool of Connections Object!
         */
        gPool = new GenericObjectPool<Connection>();
        gPool.setMaxActive(5);

        /**
         * Creates a ConnectionFactory Object Which Will Be Use by the
         * Pool to Create the Connection Object!
         */
        ConnectionFactory cf = new DriverManagerConnectionFactory(
                _dbConnectionPoolConfig.getDbUrl(), _dbConnectionPoolConfig.getDbUsername(),
                _dbConnectionPoolConfig.getDbPassword());

        // Creates a PoolableConnectionFactory That Will Wraps the Connection Object Created by the ConnectionFactory to Add Object Pooling Functionality!
        PoolableConnectionFactory pcf = new PoolableConnectionFactory(
                cf, gPool, null, null, false, true);
        return new PoolingDataSource(gPool);
    }

}
