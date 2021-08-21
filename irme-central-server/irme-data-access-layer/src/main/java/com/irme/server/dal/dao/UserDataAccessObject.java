package com.irme.server.dal.dao;

import com.irme.common.dto.AuthUserDto;
import com.irme.common.dto.UpdateUserDto;
import com.irme.server.dal.exceptions.DataAccessLayerException;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public abstract class UserDataAccessObject extends BaseDataAccessObject {

    public UserDataAccessObject(DataSource dataSource) throws Exception {
        super(dataSource);
    }

    /**
     * Insert user in database
     *
     * @return if success case or throw exception on fail
     * @exception DataAccessLayerException with possible error codes:
     * UNKNOWN_ERROR, INSERT_FAILED
     */
    public abstract void insertUser(AuthUserDto user) throws DataAccessLayerException;

    public abstract List<AuthUserDto> selectUsers(int offset, int limit, boolean sortAsc)
            throws DataAccessLayerException;

    public abstract Optional<AuthUserDto> selectUserById(int id) throws DataAccessLayerException;

    public abstract void deleteUserById(int id) throws DataAccessLayerException;

    public abstract void updateUser(UpdateUserDto updateUserDto) throws DataAccessLayerException;

    public abstract Optional<AuthUserDto> selectUserByEmail(String email)
            throws DataAccessLayerException;

    public abstract List<AuthUserDto> searchUsersByEmail(String emailKeyword, int limit)
            throws DataAccessLayerException;
}
