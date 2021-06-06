package com.irme.common.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserDto {

    private Integer id;

    private String email;

    private String passwordHash;

    private boolean banned;

    private String status;

    private String firstName;

    private String lastName;

    private String created;

    private String phone;

    private String countryCode;

    private Collection<String> roles;

    private String base64Picture;
}
