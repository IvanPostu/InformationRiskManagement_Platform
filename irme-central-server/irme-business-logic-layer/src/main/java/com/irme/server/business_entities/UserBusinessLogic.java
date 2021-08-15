package com.irme.server.business_entities;

import com.irme.common.dto.AuthUserDto;
import com.irme.server.dal.DataAccessObjectFactory;
import com.irme.server.dal.dao.UserDataAccessObject;
import com.irme.server.dal.dao.UserDataAccessObjectImpl;
import com.irme.server.dal.exceptions.DataAccessLayerException;
import lombok.extern.slf4j.Slf4j;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserBusinessLogic implements BusinessLogicEntity {
    private UserDataAccessObject userDataAccessObject;

    public UserBusinessLogic(DataSource dataSource) {
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

    public AuthUserDto getUserById(Integer userId) {
        try {
            return userDataAccessObject.selectUserById(userId).orElse(null);
        } catch (DataAccessLayerException e) {
            log.error(e.getMessage());
            return null;
        }
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

    public List<AuthUserDto> searchUsersByEmail(String emailKeyword) {
        final int USERS_COUNT_LIMIT = 20;
        List<AuthUserDto> result;

        try {
            result = userDataAccessObject.searchUsersByEmail(emailKeyword, USERS_COUNT_LIMIT);
        } catch (DataAccessLayerException e) {
            log.error(e.getMessage());
            result = new ArrayList<>();
        }

        return result;
    }

}
