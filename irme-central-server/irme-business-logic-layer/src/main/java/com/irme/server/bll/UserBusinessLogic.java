package com.irme.server.bll;

import com.irme.common.dto.AuthUserDto;
import com.irme.server.dal.DataAccessObjectFactory;
import com.irme.server.dal.connection.ConnectionConfig;
import com.irme.server.dal.connection.ConnectionConfigType;
import com.irme.server.dal.dao.UserDataAccessObject;
import com.irme.server.dal.dao.UserDataAccessObjectImpl;
import com.irme.server.dal.exceptions.DataAccessLayerException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserBusinessLogic {
    private UserDataAccessObject userDataAccessObject;

    public UserBusinessLogic(ConnectionConfigType configType) {
        HikariConfig hikariConfig = ConnectionConfig
                .getDataSourceConfig(configType);

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        DataAccessObjectFactory daoFactory = new DataAccessObjectFactory(dataSource);

        userDataAccessObject = daoFactory.createDataAccessObject(UserDataAccessObjectImpl.class);
    }

    public List<AuthUserDto> selectUsers(int offset, int limit, boolean sortAsc) {

        List<AuthUserDto> result;
        try {
            result = userDataAccessObject.selectUsers(offset, limit, sortAsc);
        } catch (DataAccessLayerException e) {
            log.error(e.getMessage());
            result = new ArrayList<>();
        }

        return result;
    }

    public boolean addUser(AuthUserDto user) {
        try {
            userDataAccessObject.insertUser(user);
        } catch (DataAccessLayerException e) {
            log.error(e.getMessage());
            return false;
        }

        return true;
    }

}
