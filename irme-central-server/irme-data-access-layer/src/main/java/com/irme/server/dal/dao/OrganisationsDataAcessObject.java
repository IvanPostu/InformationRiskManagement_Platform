package com.irme.server.dal.dao;

import com.irme.common.dto.OrganisationDto;
import com.irme.server.dal.exceptions.DataAccessLayerException;
import org.javatuples.Pair;
import javax.sql.DataSource;
import java.util.List;

public abstract class OrganisationsDataAcessObject extends BaseDataAccessObject {

    public OrganisationsDataAcessObject(DataSource dataSource) throws Exception {
        super(dataSource);
    }

    /**
     * 
     * @param userId
     * @return Tuple with organisationData and userIsAssignedToOrganisation
     * @throws DataAccessLayerException
     */
    public abstract List<Pair<OrganisationDto, Boolean>> selectAllOrganisationsWithRelatedToTheUser(
            int userId) throws DataAccessLayerException;

    public abstract List<OrganisationDto> selectAllOrganisations() throws DataAccessLayerException;

    public abstract boolean insertOrganisation(OrganisationDto organisation)
            throws DataAccessLayerException;


    public abstract boolean reassignUserToOrganisations(int userId, int[] organisationsIds)
            throws DataAccessLayerException;

}
