package com.irme.server.dal.dao;

import com.irme.common.dto.AuthUserDto;
import com.irme.server.dal.exceptions.DataAccessErrorCode;
import com.irme.server.dal.exceptions.DataAccessException;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class UserDataAccessObjectImpl extends BaseDataAccessObject implements UserDataAccessObject {

    public UserDataAccessObjectImpl(DataSource dataSource) {
        super(dataSource);
    }


    @Override
    public void insertUser(AuthUserDto user) throws DataAccessException {
        String sql = "{ call dbo.auth_user_with_info_add(?,?,?,?,?,?,?,?,?,?) }";
        int insertedUserId = -1;

        char joinChar = ';';
        StringBuilder joinedRoles = new StringBuilder();
        for (String role : user.getRoles()) {
            joinedRoles
                    .append(role)
                    .append(joinChar);
        }


        try (CallableStatement statement = super.getConnection().prepareCall(sql)) {

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPasswordHash());
            statement.setString(3, user.getStatus());
            statement.setString(4, joinedRoles.toString());
            statement.setString(5, String.valueOf(joinChar));
            statement.setString(6, user.getFirstName());
            statement.setString(7, user.getLastName());
            statement.setString(8, user.getPhone());
            statement.setString(9, user.getCountryCode());
            statement.registerOutParameter(10, Types.INTEGER);

            statement.executeUpdate();

            insertedUserId = statement.getInt(10);

        } catch (SQLException ex) {
            throw new DataAccessException(ex.getMessage(),
                    DataAccessErrorCode.INSERT_FAILED);
        } finally {
            user.setId(insertedUserId);
        }
    }

    @Override
    public List<AuthUserDto> selectUsers(int offset, int limit, boolean sortAsc)
            throws DataAccessException {
        String sql = "{ call dbo.auth_users_with_info(?,?,?,?) }";
        char joinChar = ';';
        List<AuthUserDto> result = new ArrayList<>(limit);

        try (CallableStatement statement = super.getConnection().prepareCall(sql)) {
            statement.setInt(1, offset);
            statement.setInt(2, limit);
            statement.setString(3, Character.toString(joinChar));
            statement.setInt(4, sortAsc ? 1 : 0);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Collection<String> roles = Arrays.asList(
                        rs.getString("roles").split(Character.toString(joinChar)));
                AuthUserDto u = new AuthUserDto();
                u.setId(rs.getInt("auth_user_id"));
                u.setBanned(false);
                u.setCountryCode(rs.getString("country_code"));
                u.setCreated(rs.getString("create_date"));
                u.setEmail(rs.getString("email_address"));
                u.setFirstName(rs.getString("first_name"));
                u.setLastName(rs.getString("last_name"));
                u.setPasswordHash(rs.getString("password_hash"));
                u.setPhone(rs.getString("phone"));
                u.setStatus(rs.getString("status"));
                u.setRoles(roles);

                result.add(u);
            }


        } catch (SQLException ex) {
            throw new DataAccessException(ex.getMessage(), DataAccessErrorCode.UNKNOWN_ERROR);
        } finally {
            // if(super.i)
        }

        return result;
    }

    @Override
    public Optional<AuthUserDto> selectUserById(int id) {
        return Optional.ofNullable(null);


    }

    @Override
    public Optional<AuthUserDto> selectUserByEmail(String email) throws DataAccessException {

        String sql = "{ call dbo.auth_user_by_email(?) }";
        char joinChar = ';';
        AuthUserDto result = null;

        try (CallableStatement statement = super.getConnection().prepareCall(sql)) {
            statement.setString(1, email);


            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Collection<String> roles = Arrays.asList(
                        rs.getString("roles").split(Character.toString(joinChar)));
                AuthUserDto u = new AuthUserDto();
                u.setId(rs.getInt("auth_user_id"));
                u.setBanned(false);
                u.setCountryCode(rs.getString("country_code"));
                u.setCreated(rs.getString("create_date"));
                u.setEmail(rs.getString("email_address"));
                u.setFirstName(rs.getString("first_name"));
                u.setLastName(rs.getString("last_name"));
                u.setPasswordHash(rs.getString("password_hash"));
                u.setPhone(rs.getString("phone"));
                u.setStatus(rs.getString("status"));
                u.setRoles(roles);

                result = u;
            }


        } catch (SQLException ex) {
            throw new DataAccessException(ex.getMessage(), DataAccessErrorCode.UNKNOWN_ERROR);
        }

        return Optional.ofNullable(result);
    }

    @Override
    public void deleteUserById(int id) throws DataAccessException {

        final String sql = "{ call dbo.auth_user_with_info_delete(?) }";

        try (CallableStatement statement = super.getConnection().prepareCall(sql)) {
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DataAccessException(ex.getMessage(), DataAccessErrorCode.UNKNOWN_ERROR);
        }

    }


    @Override
    public void updateUser(AuthUserDto user) throws DataAccessException {
        final String sql = "{ call dbo.auth_user_with_info_update(?,?,?,?,?,?,?,?,?,?) }";
        final char joinChar = ';';
        final StringBuilder joinedRoles = new StringBuilder();

        for (String role : user.getRoles()) {
            joinedRoles
                    .append(role)
                    .append(joinChar);
        }


        try (CallableStatement statement = super.getConnection().prepareCall(sql)) {

            statement.setInt(1, user.getId());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPasswordHash());
            statement.setString(4, user.getStatus());
            statement.setString(5, joinedRoles.toString());
            statement.setString(6, String.valueOf(joinChar));
            statement.setString(7, user.getFirstName());
            statement.setString(8, user.getLastName());
            statement.setString(9, user.getPhone());
            statement.setString(10, user.getCountryCode());

            statement.executeUpdate();

        } catch (SQLException ex) {
            throw new DataAccessException(ex.getMessage(),
                    DataAccessErrorCode.UPDATE_FAILED);
        } finally {
            // user.setId(insertedUserId);
        }

    }


}
