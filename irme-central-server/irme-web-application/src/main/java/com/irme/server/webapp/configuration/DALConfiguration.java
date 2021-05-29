package com.irme.server.webapp.configuration;

import com.irme.server.dal.UserDataAccessObject;
import com.irme.server.dal.UserDataAccessObjectImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DALConfiguration {
    @Bean
    public UserDataAccessObject getUserDAL() {
        UserDataAccessObject userDAL = new UserDataAccessObjectImpl();

        return userDAL;
    }
}
