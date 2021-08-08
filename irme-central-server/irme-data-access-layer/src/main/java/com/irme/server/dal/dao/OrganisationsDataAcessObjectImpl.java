package com.irme.server.dal.dao;

import com.irme.common.dto.OrganisationDto;
import com.irme.server.dal.exceptions.DataAccessErrorCode;
import com.irme.server.dal.exceptions.DataAccessLayerException;
import org.javatuples.Pair;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class OrganisationsDataAcessObjectImpl extends OrganisationsDataAcessObject {

    public OrganisationsDataAcessObjectImpl(DataSource dataSource) throws Exception {
        super(dataSource);
    }

    @Override
    public List<Pair<OrganisationDto, Boolean>> selectAllOrganisationsWithRelatedToTheUser(
            int userId) throws DataAccessLayerException {
        String sql = "{ CALL dbo.all_organisations_including_related_to_the_user(?) }";
        List<Pair<OrganisationDto, Boolean>> result = new ArrayList<>(100);

        try (CallableStatement statement = super.getConnection().prepareCall(sql)) {
            statement.setInt(1, userId);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {

                OrganisationDto dto = new OrganisationDto();
                dto.setBase64ImageLogo(rs.getString("base64_logo"));
                dto.setCreated(rs.getDate("created"));
                dto.setDescription(rs.getString("description"));
                dto.setName(rs.getString("name"));
                dto.setId(rs.getInt("organisation_id"));

                Boolean organisationIsRelatedToTheUser = rs.getBoolean("refers_to_organisation");

                result.add(new Pair<OrganisationDto, Boolean>(dto,
                        organisationIsRelatedToTheUser));
            }


        } catch (SQLException ex) {
            throw new DataAccessLayerException(ex.getMessage(), DataAccessErrorCode.UNKNOWN_ERROR);
        }

        return result;
    }

    @Override
    public boolean insertOrganisation(OrganisationDto organisation)
            throws DataAccessLayerException {

        String sql = "{ CALL dbo.organisation_insert(?, ?, ?, ?) }";
        int insertedOrganisationId = -1;

        try (CallableStatement statement = super.getConnection().prepareCall(sql)) {

            statement.setString(1, organisation.getName());
            statement.setString(2, organisation.getDescription());
            statement.setString(3, organisation.getBase64ImageLogo());
            statement.setInt(4, insertedOrganisationId);
            statement.registerOutParameter(4, Types.INTEGER);

            statement.executeUpdate();

            insertedOrganisationId = statement.getInt(4);

        } catch (SQLException ex) {
            throw new DataAccessLayerException(ex.getMessage(),
                    DataAccessErrorCode.INSERT_FAILED);
        }
        if (insertedOrganisationId == -1) {
            return Boolean.FALSE;
        } else {
            organisation.setId(insertedOrganisationId);
            return Boolean.TRUE;
        }
    }

}
