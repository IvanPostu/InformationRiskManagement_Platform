package irme.server.dal.dao;

import com.irme.common.dto.AuthUserDto;
import com.irme.common.dto.UpdateUserDto;
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
            user.setBase64Picture("picture64");

            userDao.beginTransaction();
            userDao.insertUser(user);
            userDao.rollbackTransactionIfExists();

            insertedUserId = user.getId();
        } catch (Exception e) {
            throw e;
        } finally {
            userDao.close();
        }

        Assertions.assertNotEquals(-1, insertedUserId);
    }

    @Tag("DAL")
    @DisplayName("selectUsersTest")
    @Test
    void selectUsersTest() throws Exception {

        try (UserDataAccessObjectImpl userDao = daoFactory.createDataAccessObject(
                UserDataAccessObjectImpl.class)) {
            List<AuthUserDto> users1 = userDao.selectUsers(0, 3, false);
            List<AuthUserDto> users2 = userDao.selectUsers(0, 3, true);

            Assertions.assertEquals(users1.size(), users2.size());

            for (int i = 0; i < users1.size(); i++) {
                Assertions.assertEquals(users1.get(i).getId(),
                        users2.get(users2.size() - i - 1).getId());
            }
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

            userDao.rollbackTransactionIfExists();

        } catch (Exception e) {
            throw e;
        } finally {
            userDao.close();
        }

    }


    @Tag("DAL")
    @Test
    public void selectUserByIdSuccessCaseTest() throws Exception {
        UserDataAccessObjectImpl userDao = daoFactory.createDataAccessObject(
                UserDataAccessObjectImpl.class);

        try {

            userDao.beginTransaction();
            AuthUserDto u1 = userDao.selectUserByEmail("testUser1@mail.ru")
                    .orElseThrow(() -> new RuntimeException("User not found"));

            AuthUserDto u2 = userDao.selectUserById(u1.getId()).get();

            Assertions.assertEquals(u1.getId(), u2.getId());

            userDao.rollbackTransactionIfExists();
        } catch (Exception e) {
            throw e;
        } finally {
            userDao.close();
        }

    }

    @Tag("DAL")
    @Test
    public void updateUserSuccessCaseTest() throws Exception {

        try (UserDataAccessObjectImpl userDao = daoFactory.createDataAccessObject(
                UserDataAccessObjectImpl.class)) {

            userDao.beginTransaction();
            AuthUserDto u1 = userDao.selectUserByEmail("testUser1@mail.ru")
                    .orElseThrow(() -> new RuntimeException("User not found"));

            UpdateUserDto updateUserDto = new UpdateUserDto(
                    u1.getId(),
                    "testUser1@mail.ru",
                    "qwerty",
                    true,
                    "BLOCKED",
                    Arrays.asList("ROLE_USER", "ROLE_ADMIN"),
                    "Bob",
                    "Fox",
                    "098767",
                    "RU",
                    "img1");

            userDao.updateUser(updateUserDto);

            AuthUserDto u2 = userDao.selectUserById(u1.getId()).get();

            Assertions.assertEquals(u2.getId(), u2.getId());
            Assertions.assertEquals(u2.getEmail(), u2.getEmail());
            Assertions.assertEquals(u2.getPasswordHash(), u2.getPasswordHash());
            Assertions.assertEquals(u2.isBanned(), u2.isBanned());
            Assertions.assertEquals(u2.getStatus(), u2.getStatus());
            Assertions.assertEquals(u2.getRoles(), u2.getRoles());
            Assertions.assertEquals(u2.getFirstName(), u2.getFirstName());
            Assertions.assertEquals(u2.getLastName(), u2.getLastName());
            Assertions.assertEquals(u2.getPhone(), u2.getPhone());
            Assertions.assertEquals(u2.getCountryCode(), u2.getCountryCode());
            Assertions.assertEquals(u2.getBase64Picture(), u2.getBase64Picture());

            userDao.rollbackTransactionIfExists();
        } catch (Exception e) {
            throw e;
        }
    }


    @Tag("DAL")
    @Test
    public void searchUsersByEmailTest() throws Exception {

        try (UserDataAccessObjectImpl userDao = daoFactory.createDataAccessObject(
                UserDataAccessObjectImpl.class)) {


            List<AuthUserDto> users1 = userDao.searchUsersByEmail("testUser", 10);
            List<AuthUserDto> users2 = userDao.searchUsersByEmail("testUser", 2);


            Assertions.assertTrue(users2.size() == 2);
            Assertions.assertTrue(users1.size() >= 3);


        } catch (Exception e) {
            throw e;
        }
    }
}
