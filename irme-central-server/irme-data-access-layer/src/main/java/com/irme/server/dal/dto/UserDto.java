package com.irme.server.dal.dto;

import java.time.LocalDate;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String passwordHash;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private LocalDate dob;

    @Getter
    @Setter
    private Boolean active;

    @Getter
    @Setter
    private Collection<String> roles;
}
