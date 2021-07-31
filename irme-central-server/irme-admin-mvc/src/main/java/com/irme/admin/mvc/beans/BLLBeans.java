package com.irme.admin.mvc.beans;

import com.irme.server.bll.CountryBusinessLogic;
import com.irme.server.bll.UserBusinessLogic;
import com.irme.server.bll.UserRolesBusinessLogic;
import com.irme.server.dal.connection.ConnectionConfigType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BLLBeans {

    @Autowired
    private ConnectionConfigType connectionConfigType;

    @Bean
    public UserRolesBusinessLogic getUserRolesBusinessLogicLayer() {
        return new UserRolesBusinessLogic(connectionConfigType);
    }

    @Bean
    public CountryBusinessLogic getCountryBusinessLogic() {
        return new CountryBusinessLogic(connectionConfigType);
    }

    @Bean
    public UserBusinessLogic getUserBusinessLogic() {
        return new UserBusinessLogic(connectionConfigType);
    }

}
