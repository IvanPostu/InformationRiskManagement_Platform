package com.irme.server.dal.dao;

import com.irme.common.dto.AuthUserDto;
import com.irme.common.dto.UpdateUserDto;
import com.irme.server.dal.exceptions.DataAccessErrorCode;
import com.irme.server.dal.exceptions.DataAccessLayerException;
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

public class UserDataAccessObjectImpl extends UserDataAccessObject {

    public UserDataAccessObjectImpl(DataSource dataSource) throws Exception {
        super(dataSource);
    }


    @Override
    public void insertUser(AuthUserDto user) throws DataAccessLayerException {
        String sql = "{ call dbo.auth_user_with_info_add(?,?,?,?,?,?,?,?,?,?,?) }";
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
            statement.setString(10, user.getBase64Picture());
            statement.registerOutParameter(11, Types.INTEGER);

            statement.executeUpdate();

            insertedUserId = statement.getInt(11);

            if (insertedUserId == -1) {
                throw new DataAccessLayerException("INSERT_FAILED",
                        DataAccessErrorCode.INSERT_FAILED);
            }

        } catch (SQLException ex) {
            throw new DataAccessLayerException(ex.getMessage(),
                    DataAccessErrorCode.INSERT_FAILED);
        } finally {
            user.setId(insertedUserId);
        }
    }

    @Override
    public List<AuthUserDto> selectUsers(int offset, int limit, boolean sortAsc)
            throws DataAccessLayerException {
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
            throw new DataAccessLayerException(ex.getMessage(), DataAccessErrorCode.UNKNOWN_ERROR);
        }

        return result;
    }

    @Override
    public Optional<AuthUserDto> selectUserById(int id) throws DataAccessLayerException {

        String sql = "{ call dbo.auth_user_by_id(?) }";
        char joinChar = ';';
        AuthUserDto result = null;

        try (CallableStatement statement = super.getConnection().prepareCall(sql)) {
            statement.setInt(1, id);

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
                u.setBase64Picture(rs.getString("base64_picture"));
                u.setRoles(roles);

                result = u;
            }


        } catch (SQLException ex) {
            throw new DataAccessLayerException(ex.getMessage(), DataAccessErrorCode.UNKNOWN_ERROR);
        }

        return Optional.ofNullable(result);
    }

    @Override
    public Optional<AuthUserDto> selectUserByEmail(String email) throws DataAccessLayerException {

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
                u.setBase64Picture(rs.getString("base64_picture"));
                u.setRoles(roles);

                result = u;
            }


        } catch (SQLException ex) {
            throw new DataAccessLayerException(ex.getMessage(), DataAccessErrorCode.UNKNOWN_ERROR);
        }

        return Optional.ofNullable(result);
    }

    @Override
    public void deleteUserById(int id) throws DataAccessLayerException {

        final String sql = "{ call dbo.auth_user_with_info_delete(?) }";

        try (CallableStatement statement = super.getConnection().prepareCall(sql)) {
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DataAccessLayerException(ex.getMessage(), DataAccessErrorCode.UNKNOWN_ERROR);
        }

    }


    @Override
    public void updateUser(UpdateUserDto updateUserDto) throws DataAccessLayerException {
        final String sql = "{ call dbo.auth_user_with_info_update(?,?,?,?,?,?,?,?,?,?,?,?) }";
        final char joinChar = ';';
        final StringBuilder joinedRoles = new StringBuilder();

        for (String role : updateUserDto.getRoles()) {
            joinedRoles
                    .append(role)
                    .append(joinChar);
        }


        try (CallableStatement statement = super.getConnection().prepareCall(sql)) {

            statement.setInt(1, updateUserDto.getUserId());
            statement.setString(2, updateUserDto.getEmail());
            statement.setString(3, updateUserDto.getPassword());
            statement.setInt(4, updateUserDto.isBanned() ? 1 : 0);
            statement.setString(5, updateUserDto.getStatus());
            statement.setString(6, joinedRoles.toString());
            statement.setString(7, String.valueOf(joinChar));
            statement.setString(8, updateUserDto.getFirstName());
            statement.setString(9, updateUserDto.getLastName());
            statement.setString(10, updateUserDto.getPhone());
            statement.setString(11, updateUserDto.getBase64Picture());
            statement.setString(12, updateUserDto.getCountryCode());

            statement.executeUpdate();

        } catch (SQLException ex) {
            throw new DataAccessLayerException(ex.getMessage(),
                    DataAccessErrorCode.UPDATE_FAILED);
        }
    }


    @Override
    public List<AuthUserDto> searchUsersByEmail(String emailKeyword, int limit)
            throws DataAccessLayerException {

        final String sql = "{ call dbo.auth_users_by_email_keyword(?,?) }";
        final char rolesJoinChar = ';';
        final List<AuthUserDto> result = new ArrayList<>();

        try (CallableStatement statement = super.getConnection().prepareCall(sql)) {
            statement.setString(1, emailKeyword);
            statement.setInt(2, limit);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Collection<String> roles = Arrays.asList(
                        rs.getString("roles").split(Character.toString(rolesJoinChar)));
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
                u.setBase64Picture(rs.getString("base64_picture"));
                u.setRoles(roles);

                result.add(u);
            }


        } catch (SQLException ex) {
            throw new DataAccessLayerException(ex.getMessage(), DataAccessErrorCode.UNKNOWN_ERROR);
        }

        return result;
    }
}
