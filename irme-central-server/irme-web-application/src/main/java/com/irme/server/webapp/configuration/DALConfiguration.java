package com.irme.server.webapp.configuration;

import com.irme.server.dal.UserDAL;
import com.irme.server.dal.UserDALImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DALConfiguration {
    @Bean
    public UserDAL getUserDAL() {
        UserDAL userDAL = new UserDALImpl();

        return userDAL;
    }
}
