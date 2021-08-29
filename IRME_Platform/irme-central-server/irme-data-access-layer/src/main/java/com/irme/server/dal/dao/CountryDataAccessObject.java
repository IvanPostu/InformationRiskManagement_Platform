package com.irme.server.dal.dao;

import com.irme.common.dto.CountryDto;
import com.irme.server.dal.exceptions.DataAccessLayerException;
import javax.sql.DataSource;
import java.util.List;

public abstract class CountryDataAccessObject extends BaseDataAccessObject {

    public CountryDataAccessObject(DataSource dataSource) throws Exception {
        super(dataSource);
    }

    public abstract List<CountryDto> getCountries() throws DataAccessLayerException;

}
