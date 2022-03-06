package com.irme.server.dal.dao;

import com.irme.common.dto.SACategoryDto;
import com.irme.server.dal.exceptions.DataAccessErrorCode;
import com.irme.server.dal.exceptions.DataAccessLayerException;

import javax.sql.DataSource;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SADataAccessObjectImpl extends SADataAccessObject {

    public SADataAccessObjectImpl(DataSource dataSource) throws Exception {
        super(dataSource);
    }

    @Override
    public List<SACategoryDto> getCategories() throws DataAccessLayerException {
        String sql = "{ call dbo.sa_categories_get() }";
        List<SACategoryDto> result = new ArrayList<>(30);

        try (CallableStatement statement = super.getConnection().prepareCall(sql)) {
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                SACategoryDto category = new SACategoryDto();
                category.setCategroyId(rs.getInt("category_id"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
                result.add(category);
            }

        } catch (SQLException ex) {
            throw new DataAccessLayerException(ex.getMessage(), DataAccessErrorCode.UNKNOWN_ERROR);
        }

        return result;
    }

}
