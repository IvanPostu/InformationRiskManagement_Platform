package com.irme.server.webapp.graphql.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode
@Data
public class OrganisationResult implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String description;

    private Date created;

    private String base64ImageLogo;
}
