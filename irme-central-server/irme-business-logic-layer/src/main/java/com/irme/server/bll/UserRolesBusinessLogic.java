package com.irme.server.bll;

import com.irme.server.dal.DataAccessObjectFactory;
import com.irme.server.dal.connection.ConnectionConfig;
import com.irme.server.dal.connection.ConnectionConfigType;
import com.irme.server.dal.dao.UserRolesAccessObject;
import com.irme.server.dal.dao.UserRolesAccessObjectImpl;
import com.irme.server.dal.exceptions.DataAccessLayerException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserRolesBusinessLogic {
    private UserRolesAccessObject userRolesAccessObject;

    public UserRolesBusinessLogic(ConnectionConfigType configType) {
        HikariConfig hikariConfig = ConnectionConfig
                .getDataSourceConfig(configType);

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        DataAccessObjectFactory daoFactory = new DataAccessObjectFactory(dataSource);

        userRolesAccessObject = daoFactory.createDataAccessObject(UserRolesAccessObjectImpl.class);
    }

    public List<String> getAllRoles() {
        try {
            return userRolesAccessObject.selectAllRoles();
        } catch (DataAccessLayerException e) {
            log.error(e.getMessage());
            return new ArrayList<>();
        }
    }

}
