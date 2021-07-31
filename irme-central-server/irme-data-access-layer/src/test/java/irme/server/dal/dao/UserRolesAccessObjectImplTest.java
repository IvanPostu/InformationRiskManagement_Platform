package irme.server.dal.dao;

import com.irme.server.dal.dao.UserRolesAccessObject;
import com.irme.server.dal.dao.UserRolesAccessObjectImpl;
import com.irme.server.dal.exceptions.DataAccessLayerException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.util.List;

public class UserRolesAccessObjectImplTest extends _BaseDataAccessObjectTest {


    @Tag(value = "DAL")
    @Test
    public void getAllRolesTest() throws DataAccessLayerException {
        UserRolesAccessObject userDao = daoFactory.createDataAccessObject(
                UserRolesAccessObjectImpl.class);


        List<String> roles = userDao.selectAllRoles();

        Assertions.assertTrue(roles.contains("ROLE_TEST"));

    }
}
