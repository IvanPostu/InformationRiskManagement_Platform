package com.irme.server.dal.dao;

import com.irme.server.dal.exceptions.DataAccessException;
import java.util.List;

public interface UserRolesAccessObject {

    List<String> selectAllRoles() throws DataAccessException;

}
