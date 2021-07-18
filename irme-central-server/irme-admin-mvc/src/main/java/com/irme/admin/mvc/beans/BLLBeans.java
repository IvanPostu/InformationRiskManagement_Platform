package com.irme.admin.mvc.beans;

import com.irme.server.bll.UserRolesBusinessLogicLayer;
import com.irme.server.dal.connection.ConnectionConfigType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BLLBeans {
   
    @Autowired
    private ConnectionConfigType connectionConfigType;

    @Bean
    public UserRolesBusinessLogicLayer getUserRolesBusinessLogicLayer(){
        return new UserRolesBusinessLogicLayer(connectionConfigType);
    }

}
