package com.irme.server.dal.connection;

import com.zaxxer.hikari.HikariConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;


public final class ConnectionConfig {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionConfig.class);

    private ConnectionConfig() {}

    private static HikariConfig CACHED_CONFIG;

    public static synchronized HikariConfig getDataSourceConfig(ConnectionConfigType configType) {

        if (CACHED_CONFIG != null) {
            logger.debug("Return datasource config value from cache");
            return CACHED_CONFIG;
        }

        Properties prop = new Properties();
        HikariConfig conenctionPoolConfig = new HikariConfig();

        try (InputStream in = ConnectionConfig.class
                .getResourceAsStream("/" + configType.configFilename())) {
            prop.load(in);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        conenctionPoolConfig.setPoolName(prop.getProperty("dataSourcePool.name"));
        conenctionPoolConfig.setMaximumPoolSize(
                Integer.valueOf(prop.getProperty("dataSourcePool.maximumPoolSize")));
        conenctionPoolConfig.setMinimumIdle(
                Integer.valueOf(prop.getProperty("dataSourcePool.minimumIdle")));
        conenctionPoolConfig.setJdbcUrl(prop.getProperty("dataSourcePool.jdbcUrl"));
        conenctionPoolConfig.setUsername(prop.getProperty("dataSourcePool.username"));
        conenctionPoolConfig.setPassword(prop.getProperty("dataSourcePool.password"));

        for (Entry<Object, Object> e : prop.entrySet()) {
            String key = (String) e.getKey();
            /**
             * Prepare generic addDataSourceProperty case
             */
            if (key.startsWith("dataSourcePool.specific.")) {
                String baseKey = key.split("dataSourcePool.specific.")[1];
                conenctionPoolConfig.addDataSourceProperty(baseKey, e.getValue());
            }
        }

        logger.debug("Return datasource config value from cache");
        return conenctionPoolConfig;

    }
}
