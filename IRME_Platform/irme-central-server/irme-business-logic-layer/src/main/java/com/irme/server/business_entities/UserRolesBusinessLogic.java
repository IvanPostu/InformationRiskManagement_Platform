package com.irme.server.business_entities;

import com.irme.server.dal.DataAccessObjectFactory;
import com.irme.server.dal.dao.UserRolesAccessObject;
import com.irme.server.dal.dao.UserRolesAccessObjectImpl;
import com.irme.server.dal.exceptions.DataAccessLayerException;
import lombok.extern.slf4j.Slf4j;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserRolesBusinessLogic implements BusinessLogicEntity {
    private UserRolesAccessObject userRolesAccessObject;

    public UserRolesBusinessLogic(DataSource dataSource) {
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
