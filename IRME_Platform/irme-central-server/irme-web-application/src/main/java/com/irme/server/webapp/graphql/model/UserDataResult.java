package com.irme.server.webapp.graphql.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Collection;

@EqualsAndHashCode
@Data
public class UserDataResult implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;

    private String email;

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
