package com.irme.server.dal.dao;

import java.util.List;
import java.util.Optional;
import com.irme.common.dto.AuthUserDto;
import com.irme.server.dal.exceptions.DataAccessException;

public interface UserDataAccessObject {

    /**
     * Insert user in database
     *
     * @return if success case or throw exception on fail
     * @exception DataAccessException with possible error codes:
     * UNKNOWN_ERROR, INSERT_FAILED
     */
    void insertUser(AuthUserDto user) throws DataAccessException;

    List<AuthUserDto> selectUsers(int offset, int limit, boolean sortAsc) throws DataAccessException;

    Optional<AuthUserDto> selectUserById(int id) throws DataAccessException;

    Optional<AuthUserDto> deleteUserById(int id) throws DataAccessException;

    Optional<AuthUserDto> selectUserByEmail(String email) throws DataAccessException;
}
