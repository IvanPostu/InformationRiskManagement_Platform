package com.irme.server.dal.dao;

import com.irme.common.dto.CountryDto;
import com.irme.server.dal.exceptions.DataAccessErrorCode;
import com.irme.server.dal.exceptions.DataAccessLayerException;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryDataAccessObjectImpl extends CountryDataAccessObject {

    public CountryDataAccessObjectImpl(DataSource dataSource) throws Exception {
        super(dataSource);
    }

    @Override
    public List<CountryDto> getCountries() throws DataAccessLayerException {

        String sql = "{ call dbo.get_countries() }";
        List<CountryDto> result = new ArrayList<>(256);

        try (CallableStatement statement = super.getConnection().prepareCall(sql)) {


            ResultSet rs = statement.executeQuery();
            while (rs.next()) {

                String countryName = rs.getString("country_name");
                String countryCode = rs.getString("country_code");
                CountryDto countryDto = new CountryDto(countryCode, countryName);

                result.add(countryDto);
            }


        } catch (SQLException ex) {
            throw new DataAccessLayerException(ex.getMessage(), DataAccessErrorCode.UNKNOWN_ERROR);
        }

        return result;
    }

}
