package com.irme.admin.mvc.beans;

import com.irme.server.bll.CountryBusinessLogic;
import com.irme.server.bll.UserBusinessLogic;
import com.irme.server.bll.UserRolesBusinessLogic;
import com.irme.server.dal.connection.ConnectionConfig;
import com.irme.server.dal.connection.ConnectionConfigType;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
public class BLLBeans {

    @Autowired
    private ConnectionConfigType connectionConfigType;

    @Bean
    public UserRolesBusinessLogic getUserRolesBusinessLogicLayer() {
        return new UserRolesBusinessLogic(getDataSource());
    }

    @Bean
    public CountryBusinessLogic getCountryBusinessLogic() {
        return new CountryBusinessLogic(getDataSource());
    }

    @Bean
    public UserBusinessLogic getUserBusinessLogic() {
        return new UserBusinessLogic(getDataSource());
    }

    @Bean
    public DataSource getDataSource() {
        HikariConfig hikariConfig = ConnectionConfig
                .getDataSourceConfig(connectionConfigType);

        return new HikariDataSource(hikariConfig);
    }

}
