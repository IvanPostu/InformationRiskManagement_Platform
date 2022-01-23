package com.irme.server.webapp.beans;


import com.irme.server.dal.connection.ConnectionConfigType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("dev")
@Configuration
public class DevelopmentOnlyBeans {

    @Bean(value = "connectionConfigType")
    public ConnectionConfigType getConnectionConfigType() {
        return ConnectionConfigType.DEVLOPMENT;
    }

}
