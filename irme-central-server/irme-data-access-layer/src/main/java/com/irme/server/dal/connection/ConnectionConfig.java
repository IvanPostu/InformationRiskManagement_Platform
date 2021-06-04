package com.irme.server.dal.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;


public final class ConnectionConfig {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionConfig.class);

    private ConnectionConfig() {}

    private static Object syncObject = new Object();
    private static HikariDataSource cachedConfig = null;

    public static HikariConfig getDataSourceConfig(String configFilename) {
        synchronized (syncObject) {

            if (cachedConfig != null) {
                logger.debug("Return datasource config value from cache");
                return cachedConfig;
            }

            Properties prop = new Properties();
            HikariConfig jdbcConfig = new HikariConfig();

            try (InputStream in = ConnectionConfig.class
                    .getResourceAsStream("/" + configFilename)) {
                prop.load(in);
            } catch (IOException e) {
                e.printStackTrace();
            }

            jdbcConfig.setPoolName(prop.getProperty("dataSourcePool.name"));
            jdbcConfig.setMaximumPoolSize(
                    Integer.valueOf(prop.getProperty("dataSourcePool.maximumPoolSize")));
            jdbcConfig.setMinimumIdle(
                    Integer.valueOf(prop.getProperty("dataSourcePool.minimumIdle")));
            jdbcConfig.setJdbcUrl(prop.getProperty("dataSourcePool.jdbcUrl"));
            jdbcConfig.setUsername(prop.getProperty("dataSourcePool.username"));
            jdbcConfig.setPassword(prop.getProperty("dataSourcePool.password"));

            for (Entry<Object, Object> e : prop.entrySet()) {
                String key = (String) e.getKey();
                /**
                 * Prepare generic addDataSourceProperty case
                 */
                if (key.startsWith("dataSourcePool.specific.")) {
                    String baseKey = key.split("dataSourcePool.specific.")[1];
                    jdbcConfig.addDataSourceProperty(baseKey, e.getValue());
                }
            }

            logger.debug("Return datasource config value from cache");
            return new HikariDataSource(jdbcConfig);
        }

    }
}
