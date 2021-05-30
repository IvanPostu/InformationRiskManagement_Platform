package com.irme.server.webapp.configuration;

import com.irme.server.bl.UserBusinessLogic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BusinessLogicLayerConfiguration {

    @Bean
    public UserBusinessLogic getUserBusinessLogic(){
        return new UserBusinessLogic();
    }

}
