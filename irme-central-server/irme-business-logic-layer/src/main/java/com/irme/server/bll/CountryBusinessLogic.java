package com.irme.server.bll;

import com.irme.common.dto.CountryDto;
import com.irme.server.dal.DataAccessObjectFactory;
import com.irme.server.dal.dao.CountryDataAccessObject;
import com.irme.server.dal.dao.CountryDataAccessObjectImpl;
import com.irme.server.dal.exceptions.DataAccessLayerException;
import lombok.extern.slf4j.Slf4j;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CountryBusinessLogic {
    private CountryDataAccessObject countryDataAccessObject;

    public CountryBusinessLogic(DataSource dataSource) {
        DataAccessObjectFactory daoFactory = new DataAccessObjectFactory(dataSource);

        countryDataAccessObject = daoFactory
                .createDataAccessObject(CountryDataAccessObjectImpl.class);
    }

    public List<CountryDto> getCountries() {
        try {
            return countryDataAccessObject.getCountries();
        } catch (DataAccessLayerException e) {
            log.error(e.getMessage());
            return new ArrayList<>();
        }
    }
}
