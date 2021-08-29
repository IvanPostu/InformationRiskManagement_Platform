package com.irme.server.webapp.graphql.model;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class SuccessAuthResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;

    private String token;

}
