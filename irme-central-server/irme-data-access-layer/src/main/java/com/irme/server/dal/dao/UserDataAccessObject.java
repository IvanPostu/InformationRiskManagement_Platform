package com.irme.server.dal.dao;

import java.util.List;
import java.util.Optional;
import com.irme.server.dal.dto.UserDto;
import com.irme.server.dal.exceptions.DataAccessException;

public interface UserDataAccessObject {

    /**
     * Insert user in database
     *
     * @return if success case or throw exception on fail
     * @exception DataAccessException with possible error codes:
     * UNKNOWN_ERROR, INSERT_FAILED
     */
    void insertUser(UserDto user);

    List<UserDto> selectUsers(int offset, int limit);

    Optional<UserDto> selectUserById(int id);

    Optional<UserDto> deleteUserById(int id);

    Optional<UserDto> selectUserByEmail(String email);
}
