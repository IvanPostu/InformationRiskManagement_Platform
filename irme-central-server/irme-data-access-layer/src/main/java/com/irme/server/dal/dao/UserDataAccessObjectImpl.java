package com.irme.server.dal.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import com.irme.server.dal.dto.AuthUserDto;

public class UserDataAccessObjectImpl extends BaseDataAccessObject implements UserDataAccessObject {

    public UserDataAccessObjectImpl(Connection connection) {
        super(connection);
    }



    public void insertUser(AuthUserDto user) {

    }

    public List<AuthUserDto> selectUsers(int offset, int limit) {
        return null;
    }

    public Optional<AuthUserDto> selectUserById(int id) {
        return Optional.ofNullable(null);


    }

    public Optional<AuthUserDto> selectUserByEmail(String email) {
        return Optional.ofNullable(null);


    }

    public Optional<AuthUserDto> deleteUserById(int id) {

        return Optional.ofNullable(null);
    }

}
