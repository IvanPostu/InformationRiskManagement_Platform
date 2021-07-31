package com.irme.admin.mvc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserModel {
    private List<String> selectedRoles;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String countryCode;
}
