package com.irme.server.dal;

import java.util.List;
import java.util.Optional;
import com.irme.server.dal.dao.UserDAO;

public interface UserDAL {

    void saveUser(UserDAO user);

    List<UserDAO> getUsers(int offset, int limit);

    Optional<UserDAO> getUserById(int id);

    Optional<UserDAO> deleteUserById(int id);

    Optional<UserDAO> getUserByEmail(String email);
}
