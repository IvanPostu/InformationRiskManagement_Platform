package com.irme.server.business_entities;

import com.irme.common.dto.OrganisationDto;
import com.irme.server.dal.DataAccessObjectFactory;
import com.irme.server.dal.dao.OrganisationsDataAcessObject;
import com.irme.server.dal.dao.OrganisationsDataAcessObjectImpl;
import com.irme.server.dal.exceptions.DataAccessLayerException;
import lombok.extern.slf4j.Slf4j;
import org.javatuples.Pair;
import javax.sql.DataSource;
import java.util.List;

@Slf4j
public class OrganisationBusinessLogic implements BusinessLogicEntity {

    private OrganisationsDataAcessObject organisationsDataAcessObject;

    public OrganisationBusinessLogic(DataSource dataSource) {
        DataAccessObjectFactory daoFactory = new DataAccessObjectFactory(dataSource);

        organisationsDataAcessObject = daoFactory
                .createDataAccessObject(OrganisationsDataAcessObjectImpl.class);
    }

    public List<Pair<OrganisationDto, Boolean>> selectAllOrganisationsIncludingRelatedToTheUser(
            int userId) {

        try {
            List<Pair<OrganisationDto, Boolean>> result = organisationsDataAcessObject
                    .selectAllOrganisationsWithRelatedToTheUser(userId);

            return result;
        } catch (DataAccessLayerException e) {
            log.error(e.getMessage());
            return null;
        }

    }

    public boolean assignUserToOrganisations(int userId, int[] organisationsIds) {

        boolean assignedWithSuccess = false;

        try {
            assignedWithSuccess = organisationsDataAcessObject
                    .reassignUserToOrganisations(userId, organisationsIds);
        } catch (DataAccessLayerException e) {
            log.error(e.getMessage());
        }

        return assignedWithSuccess;
    }

}
