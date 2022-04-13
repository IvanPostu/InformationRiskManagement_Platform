package com.irme.server.webapp.graphql.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
public class SuccessAuthResult implements Serializable {

    private static final long serialVersionUID = 12L;

    private String email;

    private String token;

    private String firstName;

    private String lastName;

}
