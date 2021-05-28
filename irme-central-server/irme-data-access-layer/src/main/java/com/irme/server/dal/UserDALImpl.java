package com.irme.server.dal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import com.irme.server.dal.dao.UserDAO;

public class UserDALImpl implements UserDAL {

    private List<UserDAO> internalList = Arrays.asList(
            new UserDAO(1, "q@mail.ru", "q", "Jimmy", "Bob", LocalDate.now(), true,
                    Arrays.asList("ROLE_USER")),
            new UserDAO(1, "w@mail.ru", "q", "Jimmy", "Bob", LocalDate.now(), true,
                    Arrays.asList("ROLE_ADMIN"))

    );
    private static int incrementor = 0;

    public void insertUser(UserDAO user) {

        if (user.getId() != null) {
            throw new IllegalArgumentException("user id should be null");
        }

        user.setId(incrementor++);

        internalList.add(user);
    }

    public List<UserDAO> selectUsers(int offset, int limit) {
        List<UserDAO> users = new ArrayList<>(limit);

        try {
            for (int i = offset; i < offset + limit; i++) {
                users.add(internalList.get(i));
            }
        } catch (IndexOutOfBoundsException e) {

        }

        return users;
    }

    public Optional<UserDAO> selectUserById(int id) {

        for (UserDAO u : internalList) {
            if (u.getId().equals(id)) {
                return Optional.ofNullable(u);
            }
        }

        return Optional.ofNullable(null);
    }

    public Optional<UserDAO> selectUserByEmail(String email) {

        for (UserDAO u : internalList) {
            if (u.getEmail().equals(email)) {
                return Optional.ofNullable(u);
            }
        }

        return Optional.ofNullable(null);
    }

    public Optional<UserDAO> deleteUserById(int id) {
        UserDAO user = null;

        for (UserDAO u : internalList) {
            if (u.getId().equals(id)) {
                user = u;
                if (internalList.remove(user)) {
                    return Optional.ofNullable(user);
                }
                break;
            }
        }

        return Optional.ofNullable(null);
    }

}
