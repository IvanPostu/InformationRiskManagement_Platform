package com.irme.server.dal;

import java.util.List;
import java.util.Optional;
import com.irme.server.dal.dao.UserDAO;

public interface UserDAL {

    void insertUser(UserDAO user);

    List<UserDAO> selectUsers(int offset, int limit);

    Optional<UserDAO> selectUserById(int id);

    Optional<UserDAO> deleteUserById(int id);

    Optional<UserDAO> selectUserByEmail(String email);
}
