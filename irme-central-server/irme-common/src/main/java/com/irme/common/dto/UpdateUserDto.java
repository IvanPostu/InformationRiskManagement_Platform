package com.irme.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Collection;

@Getter
@AllArgsConstructor
public class UpdateUserDto {
    private int userId;

    private String email;

    private String password;

    private boolean banned;

    private String status;

    private Collection<String> roles;

    private String firstName;

    private String lastName;

    private String phone;

    private String countryCode;
}
