package com.irme.admin.mvc.controller.api;

import com.irme.server.bll.UserRolesBusinessLogicLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
public class UsersApiController {

    @Autowired
    private UserRolesBusinessLogicLayer userRolesBusinessLogicLayer;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/allRoles")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getAll() {
        List<String> result = userRolesBusinessLogicLayer.getAllRoles();

        return result;
    }
}
