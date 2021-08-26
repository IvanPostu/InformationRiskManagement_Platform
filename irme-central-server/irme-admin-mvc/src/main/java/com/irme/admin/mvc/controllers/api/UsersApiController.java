package com.irme.admin.mvc.controllers.api;

import com.irme.admin.mvc.models.CreateUserModel;
import com.irme.common.dto.AuthUserDto;
import com.irme.server.business_entities.UserBusinessLogic;
import com.irme.server.business_entities.UserRolesBusinessLogic;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/users")
public class UsersApiController {

    @Autowired
    private UserRolesBusinessLogic userRolesBusinessLogicLayer;

    @Autowired
    private UserBusinessLogic userBusinessLogic;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/allRoles")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getAllRoles() {
        List<String> result = userRolesBusinessLogicLayer.getAllRoles();

        return result;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/getAll")
    public ResponseEntity<?> getAllUsers(
            @RequestParam(name = "offset") int offset,
            @RequestParam(name = "limit") int limit,
            @RequestParam(name = "sortAsc") int sortAsc

    ) {
        List<AuthUserDto> result = userBusinessLogic
                .selectUsers(offset, limit, sortAsc == 1 ? true : false);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody CreateUserModel userModel) {

        AuthUserDto user = new AuthUserDto();

        user.setBanned(false);
        user.setBase64Picture("");
        user.setCreated("");
        user.setPhone("123456789");
        user.setStatus("ACTIVE");

        user.setEmail(userModel.getEmail());
        user.setFirstName(userModel.getLastName());
        user.setLastName(userModel.getFirstName());
        user.setPasswordHash(userModel.getPassword());
        user.setRoles(userModel.getSelectedRoles());
        user.setCountryCode(userModel.getCountryCode());

        if (userBusinessLogic.addUser(user)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/searchByEmail")
    public ResponseEntity<List<Pair<Integer, String>>> searchByEmail(
            @RequestParam(name = "emailKeyword") String emailKeyword) {
        List<AuthUserDto> users = userBusinessLogic
                .getUsersByEmail(emailKeyword);

        List<Pair<Integer, String>> resultList = users
                .stream()
                .map(user -> new Pair<Integer, String>(user.getId(), user.getEmail()))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.CREATED).body(resultList);
    }
}
