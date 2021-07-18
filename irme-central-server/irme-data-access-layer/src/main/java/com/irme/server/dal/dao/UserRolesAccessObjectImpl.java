package com.irme.server.dal.dao;

import com.irme.server.dal.exceptions.DataAccessErrorCode;
import com.irme.server.dal.exceptions.DataAccessException;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRolesAccessObjectImpl extends BaseDataAccessObject
        implements UserRolesAccessObject {

    public UserRolesAccessObjectImpl(DataSource dataSource) throws Exception {
        super(dataSource);
    }

    @Override
    public List<String> selectAllRoles() throws DataAccessException {
        String sql = "{ call dbo.auth_user_roles_all() }";
        List<String> result = new ArrayList<>();

        try (CallableStatement statement = super.getConnection().prepareCall(sql)) {

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                result.add(rs.getString("role_name"));
            }


        } catch (SQLException ex) {
            throw new DataAccessException(ex.getMessage(), DataAccessErrorCode.UNKNOWN_ERROR);
        }

        return result;
    }

}
