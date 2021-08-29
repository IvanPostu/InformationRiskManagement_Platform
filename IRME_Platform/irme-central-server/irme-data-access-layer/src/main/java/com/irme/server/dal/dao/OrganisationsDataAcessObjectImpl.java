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
import java.util.StringJoiner;
import java.util.stream.Collectors;

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
    public boolean saveOrganisation(OrganisationDto organisation)
            throws DataAccessLayerException {

        String sql = "{ CALL dbo.organisation_save(?, ?, ?, ?, ?) }";
        int insertedOrganisationId = -1;
        boolean isInsert = organisation.getId() == null;

        try (CallableStatement statement = super.getConnection().prepareCall(sql)) {

            int id = isInsert ? -1 : organisation.getId();

            statement.setInt(1, id);
            statement.setString(2, organisation.getName());
            statement.setString(3, organisation.getDescription());
            statement.setString(4, organisation.getBase64ImageLogo());
            statement.setInt(5, insertedOrganisationId);
            statement.registerOutParameter(5, Types.INTEGER);

            statement.executeUpdate();

            insertedOrganisationId = statement.getInt(5);

        } catch (SQLException ex) {
            throw new DataAccessLayerException(ex.getMessage(),
                    DataAccessErrorCode.INSERT_FAILED);
        }
        if (insertedOrganisationId == -1) {
            return Boolean.FALSE;
        } else {
            if (isInsert) {
                organisation.setId(insertedOrganisationId);
            }
            return Boolean.TRUE;
        }
    }

    @Override
    public boolean reassignUserToOrganisations(int userId, int[] organisationsIds)
            throws DataAccessLayerException {
        String sql = "{ CALL dbo.reassign_user_to_organisations( ?, ?, ? ) }";
        int isSuccess = -1;

        try (CallableStatement statement = super.getConnection().prepareCall(sql)) {

            statement.setInt(1, userId);

            StringJoiner organisationsIdsParam = new StringJoiner(",");
            for (int i = 0; i < organisationsIds.length; i++) {
                organisationsIdsParam.add(String.valueOf(organisationsIds[i]));
            }


            statement.setString(2, organisationsIdsParam.toString());
            statement.setInt(3, isSuccess);
            statement.registerOutParameter(3, Types.INTEGER);

            statement.executeUpdate();

            isSuccess = statement.getInt(3);

        } catch (SQLException ex) {
            throw new DataAccessLayerException(ex.getMessage(),
                    DataAccessErrorCode.INSERT_FAILED);
        }

        return (isSuccess == 1);
    }

    @Override
    public List<OrganisationDto> selectAllOrganisations() throws DataAccessLayerException {
        List<Pair<OrganisationDto, Boolean>> dbResult;
        dbResult = selectAllOrganisationsWithRelatedToTheUser(-1);


        return dbResult.stream().map(p -> p.getValue0()).collect(Collectors.toList());
    }

    @Override
    public OrganisationDto selectOrganisationById(int id) throws DataAccessLayerException {
        String sql = "{ CALL dbo.organisation_get_by_id(?) }";
        OrganisationDto result = null;

        try (CallableStatement statement = super.getConnection().prepareCall(sql)) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {

                OrganisationDto dto = new OrganisationDto();
                dto.setBase64ImageLogo(rs.getString("base64_logo"));
                dto.setCreated(rs.getDate("created"));
                dto.setDescription(rs.getString("description"));
                dto.setName(rs.getString("name"));
                dto.setId(rs.getInt("organisation_id"));

                result = dto;
            }


        } catch (SQLException ex) {
            throw new DataAccessLayerException(ex.getMessage(), DataAccessErrorCode.UNKNOWN_ERROR);
        }

        return result;
    }

}
