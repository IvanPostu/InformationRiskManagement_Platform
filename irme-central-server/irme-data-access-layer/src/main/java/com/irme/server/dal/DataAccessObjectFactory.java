package com.irme.server.dal;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.irme.server.dal.dao.BaseDataAccessObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.Getter;

public class DataAccessObjectFactory {

    private static final Logger logger = LoggerFactory.getLogger(DataAccessObjectFactory.class);

    @Getter
    private final String connectionString;

    @Getter
    private final Connection connection;

    public DataAccessObjectFactory(final String connectionString) {
        this.connectionString = connectionString;
        this.connection = tryToConnect();
    }

    private Connection tryToConnect() {
        try {
            Connection connection = DriverManager.getConnection(this.getConnectionString());
            logger.info("DataAccessObjectFactory created with success");
            return connection;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new Error(e);
        }
    }

    /**
     * Create specific DataAccessObject
     * This method is slow because it uses reflection
     */
    @SuppressWarnings({"unchecked"})
    public <T extends BaseDataAccessObject> T createDataAccessObject(
            Class<? extends BaseDataAccessObject> targetClass) {

        T result = null;
        Class<?>[] argsType = new Class<?>[] {Connection.class};
        try {
            Constructor<? extends BaseDataAccessObject> ctor = targetClass
                    .getConstructor(argsType);

            result = (T) ctor.newInstance(this.getConnection());
        } catch (NoSuchMethodException
                | SecurityException
                | InstantiationException
                | IllegalAccessException
                | InvocationTargetException e) {
            logger.error(e.getMessage());
        }


        return result;
    }

}
