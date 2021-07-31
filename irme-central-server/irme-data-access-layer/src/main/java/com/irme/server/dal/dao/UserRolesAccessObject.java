package com.irme.server.dal.dao;

import com.irme.server.dal.exceptions.DataAccessLayerException;
import java.util.List;

public interface UserRolesAccessObject {

    List<String> selectAllRoles() throws DataAccessLayerException;

}
