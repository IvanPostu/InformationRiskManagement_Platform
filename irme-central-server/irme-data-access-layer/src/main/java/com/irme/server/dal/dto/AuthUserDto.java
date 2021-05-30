package com.irme.server.dal.dto;


import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserDto {

    private Integer id;

    private String email;

    private String passwordHash;

    private Boolean banned;

    private String status;

    private String firstName;

    private String lastName;

    private String created;

    private String phone;

    private String countryCode;

    private Collection<String> roles;
}
