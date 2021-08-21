package irme.server.dal.dao;

import com.irme.common.dto.AuthUserDto;
import com.irme.common.dto.OrganisationDto;
import com.irme.server.dal.dao.OrganisationsDataAcessObject;
import com.irme.server.dal.dao.OrganisationsDataAcessObjectImpl;
import com.irme.server.dal.dao.UserDataAccessObject;
import com.irme.server.dal.dao.UserDataAccessObjectImpl;
import org.javatuples.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.List;

public class OrganisationsDataAcessObjectImplTest extends _BaseDataAccessObjectTest {

    @Tag(value = "DAL")
    @Test
    public void selectAllOrganisationsIncludingRelatedToTheUserTest() throws Exception {
        OrganisationsDataAcessObject dao = daoFactory.createDataAccessObject(
                OrganisationsDataAcessObjectImpl.class);

        List<Pair<OrganisationDto, Boolean>> organisations = dao
                .selectAllOrganisationsWithRelatedToTheUser(-1);

        Assertions.assertNotNull(organisations);
        Assertions.assertTrue(organisations.size() >= 5);
    }

    @Tag(value = "DAL")
    @Test
    public void selectAllOrganisationsTest() throws Exception {
        OrganisationsDataAcessObject dao = daoFactory.createDataAccessObject(
                OrganisationsDataAcessObjectImpl.class);

        List<OrganisationDto> organisations = dao
                .selectAllOrganisations();

        Assertions.assertNotNull(organisations);
        Assertions.assertTrue(organisations.size() >= 5);
    }

    @Tag(value = "DAL")
    @Test
    public void insertOrganisationTest() throws Exception {
        OrganisationsDataAcessObject dao = daoFactory.createDataAccessObject(
                OrganisationsDataAcessObjectImpl.class);


        int insertedUserId = -1;

        try {

            OrganisationDto organisationDto = new OrganisationDto();
            organisationDto.setName("helloworld11");
            organisationDto.setBase64ImageLogo("qwe");
            organisationDto.setDescription("description 134134");
            organisationDto.setCreated(new Date());

            dao.beginTransaction();

            dao.insertOrganisation(organisationDto);
            insertedUserId = organisationDto.getId();
            Assertions.assertNotEquals(-1, insertedUserId);

            dao.rollbackTransactionIfExists();


        } catch (Exception e) {
            throw e;
        } finally {
            dao.close();
        }

    }

    @Tag(value = "DAL")
    @Test
    public void reassignUserToOrganisations() throws Exception {
        UserDataAccessObject userDao = daoFactory.createDataAccessObject(
                UserDataAccessObjectImpl.class);

        OrganisationsDataAcessObject organisationDao = daoFactory.createDataAccessObject(
                OrganisationsDataAcessObjectImpl.class);

        try {

            organisationDao.beginTransaction();

            AuthUserDto u1 = userDao.selectUserByEmail("testUser1@mail.ru")
                    .orElseThrow(() -> new RuntimeException("User not found"));

            List<OrganisationDto> organisations = organisationDao.selectAllOrganisations();

            int userId = u1.getId();
            int organisationId1 = organisations.get(0).getId();
            int organisationId2 = organisations.get(1).getId();


            Assertions.assertTrue(
                    organisationDao.reassignUserToOrganisations(userId, new int[] {
                            organisationId1,
                            organisationId2
                    }));


            List<Pair<OrganisationDto, Boolean>> organisationsWithRelatedToUserInfo;
            organisationsWithRelatedToUserInfo = organisationDao
                    .selectAllOrganisationsWithRelatedToTheUser(userId);

            // Check if user is assigned to organisation
            Assertions.assertTrue(organisationsWithRelatedToUserInfo.get(0).getValue1());
            Assertions.assertTrue(organisationsWithRelatedToUserInfo.get(1).getValue1());


            organisationDao.rollbackTransactionIfExists();
        } catch (Exception e) {
            throw e;
        } finally {
            userDao.close();
            organisationDao.close();
        }
    }


}
