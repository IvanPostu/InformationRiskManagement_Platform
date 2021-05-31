package com.irme.server.dal;

import java.sql.SQLException;
import java.util.Arrays;
import com.irme.common.dto.AuthUserDto;
import com.irme.server.dal.dao.UserDataAccessObject;
import com.irme.server.dal.dao.UserDataAccessObjectImpl;

/**
 * Hello world!
 *
 */
public class App {
    public static String message() {
        return "D2";
    }

    public static void main(String[] args) throws SQLException {
        String dbURL =
                "jdbc:sqlserver://0.0.0.0:1433;databaseName=InformationRiskManagementDatabase;user=sa;password=Testing1122990";
        DataAccessObjectFactory factory = new DataAccessObjectFactory(dbURL);
        UserDataAccessObject userDao = factory.createDataAccessObject(UserDataAccessObjectImpl.class);

        AuthUserDto user = new AuthUserDto();
        user.setEmail("ww@mail.rumd");
        user.setBanned(false);
        user.setCountryCode("MD");
        user.setCreated("");
        user.setFirstName("J");
        user.setLastName("K");
        user.setPasswordHash("q");
        user.setStatus("ACTIVE");
        user.setRoles(Arrays.asList("ROLE_USER"));
        user.setPhone("134");

        // userDao.insertUser(user);

        char c = 'a';
    }
}
