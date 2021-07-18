package com.irme.server.bll;

import com.irme.server.dal.DataAccessObjectFactory;
import com.irme.server.dal.connection.ConnectionConfig;
import com.irme.server.dal.connection.ConnectionConfigType;
import com.irme.server.dal.dao.UserRolesAccessObject;
import com.irme.server.dal.dao.UserRolesAccessObjectImpl;
import com.irme.server.dal.exceptions.DataAccessException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserRolesBusinessLogicLayer {
    private UserRolesAccessObject userRolesAccessObject;

    public UserRolesBusinessLogicLayer(ConnectionConfigType configType) {
        HikariConfig hikariConfig = ConnectionConfig
                .getDataSourceConfig(configType);

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        DataAccessObjectFactory daoFactory = new DataAccessObjectFactory(dataSource);

        userRolesAccessObject = daoFactory.createDataAccessObject(UserRolesAccessObjectImpl.class);
    }

    public List<String> getAllRoles() {
        try {
            return userRolesAccessObject.selectAllRoles();
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            return new ArrayList<>();
        }
    }

}
