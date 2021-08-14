package com.irme.server.dal.dao;

import com.irme.common.dto.AuthUserDto;
import com.irme.common.dto.UpdateUserDto;
import com.irme.server.dal.exceptions.DataAccessLayerException;
import java.util.List;
import java.util.Optional;

public interface UserDataAccessObject {

    /**
     * Insert user in database
     *
     * @return if success case or throw exception on fail
     * @exception DataAccessLayerException with possible error codes:
     * UNKNOWN_ERROR, INSERT_FAILED
     */
    void insertUser(AuthUserDto user) throws DataAccessLayerException;

    List<AuthUserDto> selectUsers(int offset, int limit, boolean sortAsc)
            throws DataAccessLayerException;

    Optional<AuthUserDto> selectUserById(int id) throws DataAccessLayerException;

    void deleteUserById(int id) throws DataAccessLayerException;

    void updateUser(UpdateUserDto updateUserDto) throws DataAccessLayerException;

    Optional<AuthUserDto> selectUserByEmail(String email) throws DataAccessLayerException;

    List<AuthUserDto> searchUsersByEmail(String emailKeyword, int limit)
            throws DataAccessLayerException;
}
