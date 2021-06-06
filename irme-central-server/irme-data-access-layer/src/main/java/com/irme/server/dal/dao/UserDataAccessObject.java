package com.irme.server.dal.dao;

import com.irme.common.dto.AuthUserDto;
import com.irme.common.dto.UpdateUserDto;
import com.irme.server.dal.exceptions.DataAccessException;
import java.util.List;
import java.util.Optional;

public interface UserDataAccessObject {

    /**
     * Insert user in database
     *
     * @return if success case or throw exception on fail
     * @exception DataAccessException with possible error codes:
     * UNKNOWN_ERROR, INSERT_FAILED
     */
    void insertUser(AuthUserDto user) throws DataAccessException;

    List<AuthUserDto> selectUsers(int offset, int limit, boolean sortAsc)
            throws DataAccessException;

    Optional<AuthUserDto> selectUserById(int id) throws DataAccessException;

    void deleteUserById(int id) throws DataAccessException;

    void updateUser(UpdateUserDto updateUserDto) throws DataAccessException;

    Optional<AuthUserDto> selectUserByEmail(String email) throws DataAccessException;
}
