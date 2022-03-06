package com.irme.server.webapp.beans;

import com.irme.server.business_entities.CountryBusinessLogic;
import com.irme.server.business_entities.OrganisationBusinessLogic;
import com.irme.server.business_entities.SABusinessLogic;
import com.irme.server.business_entities.UserBusinessLogic;
import com.irme.server.business_entities.UserRolesBusinessLogic;
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
    public SABusinessLogic getSABusinessLogic() {
        return new SABusinessLogic(getDataSource());
    }

    @Bean
    public DataSource getDataSource() {
        HikariConfig hikariConfig = ConnectionConfig
                .getDataSourceConfig(connectionConfigType);

        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public OrganisationBusinessLogic getOrganisationBusinessLogic() {
        return new OrganisationBusinessLogic(getDataSource());
    }

}
