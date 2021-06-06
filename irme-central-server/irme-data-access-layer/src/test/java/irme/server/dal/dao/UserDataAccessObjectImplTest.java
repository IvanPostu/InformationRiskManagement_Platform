package irme.server.dal.dao;

import com.irme.common.dto.AuthUserDto;
import com.irme.server.dal.dao.UserDataAccessObjectImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class UserDataAccessObjectImplTest extends _BaseDataAccessObjectTest {

    @Tag(value = "DAL")
    @Test
    public void insertUserTest() throws Exception {
        UserDataAccessObjectImpl userDao = daoFactory.createDataAccessObject(
                UserDataAccessObjectImpl.class);

        int insertedUserId = -1;

        try {

            AuthUserDto user = new AuthUserDto();
            user.setEmail("zuma@mail.rumd");
            user.setBanned(false);
            user.setCountryCode("MD");
            user.setCreated("");
            user.setFirstName("J");
            user.setLastName("K");
            user.setPasswordHash("q");
            user.setStatus("ACTIVE");
            user.setRoles(Arrays.asList("ROLE_USER"));
            user.setPhone("134");

            userDao.beginTransaction();
            userDao.insertUser(user);

            insertedUserId = user.getId();
        } catch (Exception e) {
            throw e;
        } finally {
            userDao.rollbackTransactionIfExists();
        }

        Assertions.assertNotEquals(-1, insertedUserId);
    }

    @Tag("DAL")
    @DisplayName("selectUsersTest")
    @Test
    void selectUsersTest() throws Exception {

        UserDataAccessObjectImpl userDao = daoFactory.createDataAccessObject(
                UserDataAccessObjectImpl.class);

        List<AuthUserDto> users1 = userDao.selectUsers(0, 3, false);
        List<AuthUserDto> users2 = userDao.selectUsers(0, 3, true);

        Assertions.assertEquals(users1.size(), users2.size());

        for (int i = 0; i < users1.size(); i++) {
            Assertions.assertEquals(users1.get(i).getId(),
                    users2.get(users2.size() - i - 1).getId());
        }
    }

    @Tag("DAL")
    @Test
    void selectUserByEmailTest() throws Exception {

        UserDataAccessObjectImpl userDao = daoFactory.createDataAccessObject(
                UserDataAccessObjectImpl.class);

        userDao.selectUserByEmail("testUser1@mail.ru")
                .orElseThrow(() -> new RuntimeException("User not found"));

    }

    @Tag("DAL")
    @Test
    public void deleteUserByIdSuccessCaseTest() throws Exception {
        UserDataAccessObjectImpl userDao = daoFactory.createDataAccessObject(
                UserDataAccessObjectImpl.class);

        try {

            userDao.beginTransaction();
            AuthUserDto u1 = userDao.selectUserByEmail("testUser1@mail.ru")
                    .orElseThrow(() -> new RuntimeException("User not found"));

            userDao.deleteUserById(u1.getId());

            Assertions.assertThrows(NoSuchElementException.class, () -> {
                userDao.selectUserByEmail("testUser1@mail.ru").get();
            });

        } catch (Exception e) {
            throw e;
        } finally {
            userDao.rollbackTransactionIfExists();
            userDao.close();
        }

    }

}
