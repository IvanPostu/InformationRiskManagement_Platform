package com.irme.server.dal.dao;

import com.irme.common.dto.OrganisationDto;
import com.irme.common.dto.OrganisationIncludingRelatedToTheUserDto;
import com.irme.server.dal.exceptions.DataAccessLayerException;
import javax.sql.DataSource;
import java.util.List;

public abstract class OrganisationsDataAcessObject extends BaseDataAccessObject {

    public OrganisationsDataAcessObject(DataSource dataSource) throws Exception {
        super(dataSource);
    }

    public abstract List<OrganisationIncludingRelatedToTheUserDto> selectAllOrganisationsIncludingRelatedToTheUser(
            int userId) throws DataAccessLayerException;

    public abstract boolean insertOrganisation(OrganisationDto organisation)
            throws DataAccessLayerException;


}
