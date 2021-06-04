package com.irme.server.dal;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import javax.sql.DataSource;
import com.irme.server.dal.dao.BaseDataAccessObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataAccessObjectFactory {

    private static final Logger logger = LoggerFactory.getLogger(DataAccessObjectFactory.class);
    private final DataSource dataSource;

    public DataAccessObjectFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    /**
     * Create specific DataAccessObject
     * This method is slow because it uses reflection
     */
    @SuppressWarnings({"unchecked"})
    public <T extends BaseDataAccessObject> T createDataAccessObject(
            Class<? extends BaseDataAccessObject> targetClass) {

        T result = null;
        Class<?>[] argsType = new Class<?>[] {DataSource.class};
        try {
            Constructor<? extends BaseDataAccessObject> ctor = targetClass
                    .getConstructor(argsType);

            result = (T) ctor.newInstance(dataSource);

        } catch (NoSuchMethodException
                | SecurityException
                | InstantiationException
                | IllegalArgumentException
                | InvocationTargetException
                | IllegalAccessException e) {
            logger.error(e.getMessage());
        }


        return result;
    }

}
