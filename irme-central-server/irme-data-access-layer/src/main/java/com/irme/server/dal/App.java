package com.irme.server.dal;

import com.irme.common.dto.AuthUserDto;
import com.irme.server.dal.connection.ConnectionConfig;
import com.irme.server.dal.dao.UserDataAccessObject;
import com.irme.server.dal.dao.UserDataAccessObjectImpl;
import com.irme.server.dal.exceptions.DataAccessException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App {
    public static String message() {
        return "D2";
    }

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws SQLException {

        HikariConfig hikariConfig = ConnectionConfig.getDataSourceConfig("app.dev.properties");
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        DataAccessObjectFactory factory = new DataAccessObjectFactory(dataSource);
        UserDataAccessObject userDao = factory.createDataAccessObject(
                UserDataAccessObjectImpl.class);


        AuthUserDto user = new AuthUserDto();
        user.setEmail("az21ww@mail.rumd");
        user.setBanned(false);
        user.setCountryCode("MD");
        user.setCreated("");
        user.setFirstName("J");
        user.setLastName("K");
        user.setPasswordHash("q");
        user.setStatus("ACTIVE");
        user.setRoles(Arrays.asList("ROLE_USER"));
        user.setPhone("134");

        try {
            userDao.insertUser(user);
        } catch (DataAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        char c = 'a';
    }
}
