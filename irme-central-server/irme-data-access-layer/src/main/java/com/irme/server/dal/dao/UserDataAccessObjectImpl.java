package com.irme.server.dal.dao;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import com.irme.server.dal.dto.UserDto;

public class UserDataAccessObjectImpl extends BaseDataAccessObject implements UserDataAccessObject {

    public UserDataAccessObjectImpl(Connection connection) {
        super(connection);
    }

    private List<UserDto> internalList = Arrays.asList(
            new UserDto(1, "q@mail.ru", "q", "Jimmy", "Bob", LocalDate.now(), true,
                    Arrays.asList("ROLE_USER")),
            new UserDto(1, "w@mail.ru", "q", "Jimmy", "Bob", LocalDate.now(), true,
                    Arrays.asList("ROLE_ADMIN"))

    );
    private static int incrementor = 0;

    public void insertUser(UserDto user) {

        if (user.getId() != null) {
            throw new IllegalArgumentException("user id should be null");
        }

        user.setId(incrementor++);

        internalList.add(user);
    }

    public List<UserDto> selectUsers(int offset, int limit) {
        List<UserDto> users = new ArrayList<>(limit);

        try {
            for (int i = offset; i < offset + limit; i++) {
                users.add(internalList.get(i));
            }
        } catch (IndexOutOfBoundsException e) {

        }

        return users;
    }

    public Optional<UserDto> selectUserById(int id) {

        for (UserDto u : internalList) {
            if (u.getId().equals(id)) {
                return Optional.ofNullable(u);
            }
        }

        return Optional.ofNullable(null);
    }

    public Optional<UserDto> selectUserByEmail(String email) {

        for (UserDto u : internalList) {
            if (u.getEmail().equals(email)) {
                return Optional.ofNullable(u);
            }
        }

        return Optional.ofNullable(null);
    }

    public Optional<UserDto> deleteUserById(int id) {
        UserDto user = null;

        for (UserDto u : internalList) {
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
