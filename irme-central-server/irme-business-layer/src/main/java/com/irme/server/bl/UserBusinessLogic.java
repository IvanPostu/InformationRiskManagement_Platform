package com.irme.server.bl;

import com.irme.server.dal.DataAccessObjectFactory;
import com.irme.server.dal.dao.UserDataAccessObject;
import com.irme.server.dal.dao.UserDataAccessObjectImpl;

public class UserBusinessLogic {
    String connectionStr = "jdbc:sqlserver://0.0.0.0:1433;databaseName=InformationRiskManagementDatabase;user=sa;password=Testing1122990";

    // private final UserDataAccessObject dao;

    public UserBusinessLogic(){
        // DataAccessObjectFactory daoFactory = new DataAccessObjectFactory(connectionStr);
        // dao = daoFactory.createDataAccessObject(UserDataAccessObjectImpl.class);
    }

        // private static final Logger logger = LoggerFactory.getLogger(UserBusinessLogic.class);

    // boolean addUser(UserDto user) {

    // }

    // List<UserDto> getUsers(int offset, int limit) {

    // }

    // Optional<UserDto> getUserById(int id) {

    // }

    // Optional<UserDto> removeUserById(int id) {

    // }

    // public UserDto getUserByEmail(String email) {

    //     UserDto user = dao.selectUserByEmail(email).orElseThrow(() -> new RuntimeException());

    //     return user;
    // }

}
