package irme.server.dal.dao;

import com.irme.common.dto.CountryDto;
import com.irme.server.dal.dao.CountryDataAccessObject;
import com.irme.server.dal.dao.CountryDataAccessObjectImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.util.List;

public class CountryDataAccessObjectImplTest extends _BaseDataAccessObjectTest {

    @Tag(value = "DAL")
    @Test
    public void getCountriesTest() throws Exception {
        try (CountryDataAccessObject countryDao = daoFactory.createDataAccessObject(
                CountryDataAccessObjectImpl.class)) {

            List<CountryDto> countries = countryDao.getCountries();
            Assertions.assertNotNull(countries);
            Assertions.assertTrue(countries.size() > 10);
        }
    }

}
