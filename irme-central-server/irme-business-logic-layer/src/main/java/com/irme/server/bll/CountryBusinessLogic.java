package com.irme.server.bll;

import com.irme.common.dto.CountryDto;
import com.irme.server.dal.DataAccessObjectFactory;
import com.irme.server.dal.connection.ConnectionConfig;
import com.irme.server.dal.connection.ConnectionConfigType;
import com.irme.server.dal.dao.CountryDataAccessObject;
import com.irme.server.dal.dao.CountryDataAccessObjectImpl;
import com.irme.server.dal.exceptions.DataAccessException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CountryBusinessLogic {
    private CountryDataAccessObject countryDataAccessObject;

    public CountryBusinessLogic(ConnectionConfigType configType) {
        HikariConfig hikariConfig = ConnectionConfig
                .getDataSourceConfig(configType);

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        DataAccessObjectFactory daoFactory = new DataAccessObjectFactory(dataSource);

        countryDataAccessObject = daoFactory
                .createDataAccessObject(CountryDataAccessObjectImpl.class);
    }

    public List<CountryDto> getCountries() {
        try {
            return countryDataAccessObject.getCountries();
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            return new ArrayList<>();
        }
    }
}
