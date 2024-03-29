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
import java.util.Optional;

@Slf4j
public class OrganisationBusinessLogic implements BusinessLogicEntity {

    private OrganisationsDataAcessObject organisationsDataAcessObject;

    public OrganisationBusinessLogic(DataSource dataSource) {
        DataAccessObjectFactory daoFactory = new DataAccessObjectFactory(dataSource);

        organisationsDataAcessObject = daoFactory
                .createDataAccessObject(OrganisationsDataAcessObjectImpl.class);
    }

    public Optional<OrganisationDto> getOrganisationById(int organisationId)
            throws BusinessLogicException {
        OrganisationDto result;

        try {
            result = organisationsDataAcessObject
                    .selectOrganisationById(organisationId);
            return Optional.ofNullable(result);
        } catch (DataAccessLayerException e) {
            log.error(e.getMessage());
            throw new BusinessLogicException("getOrganisationById BLL method caused an error",
                    e);
        }
    }

    public List<OrganisationDto> selectAllOrganisations() throws BusinessLogicException {
        try {
            List<OrganisationDto> result = organisationsDataAcessObject
                    .selectAllOrganisations();

            return result;
        } catch (DataAccessLayerException e) {
            log.error(e.getMessage());
            throw new BusinessLogicException("selectAllOrganisations BLL method caused an error",
                    e);
        }
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

    public boolean saveOrganisation(OrganisationDto organisation) {
        boolean result = false;

        try {
            result = organisationsDataAcessObject.saveOrganisation(organisation);
        } catch (DataAccessLayerException e) {
            log.error(e.getMessage());
        }

        return result;
    }

}
