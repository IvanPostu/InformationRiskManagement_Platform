package irme.server.dal.dao;

import com.irme.common.dto.OrganisationDto;
import com.irme.common.dto.OrganisationIncludingRelatedToTheUserDto;
import com.irme.server.dal.dao.OrganisationsDataAcessObject;
import com.irme.server.dal.dao.OrganisationsDataAcessObjectImpl;
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

        List<OrganisationIncludingRelatedToTheUserDto> organisations = dao
                .selectAllOrganisationsIncludingRelatedToTheUser(-1);

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


}
