package com.irme.server.dal.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Optional;
import com.irme.server.dal.dto.AuthUserDto;

public class UserDataAccessObjectImpl extends BaseDataAccessObject implements UserDataAccessObject {

    public UserDataAccessObjectImpl(Connection connection) {
        super(connection);
    }



    public void insertUser(AuthUserDto user) {
        String sql = "{ call dbo.auth_user_with_info_add(?,?,?,?,?,?,?,?,?,?) }";
        int insertedUserId = -1;

        char joinChar = ';';
        StringBuilder joinedRoles = new StringBuilder();
        for(String role : user.getRoles()){
            joinedRoles
                .append(role)
                .append(joinChar);
        }

        try(CallableStatement statement = super.getConnection().prepareCall(sql)){
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
        }catch(SQLException ex){

        }finally{
            user.setId(insertedUserId);
        }


    }

    public List<AuthUserDto> selectUsers(int offset, int limit) {
        return null;
    }

    public Optional<AuthUserDto> selectUserById(int id) {
        return Optional.ofNullable(null);


    }

    public Optional<AuthUserDto> selectUserByEmail(String email) {
        return Optional.ofNullable(null);


    }

    public Optional<AuthUserDto> deleteUserById(int id) {

        return Optional.ofNullable(null);
    }

}
