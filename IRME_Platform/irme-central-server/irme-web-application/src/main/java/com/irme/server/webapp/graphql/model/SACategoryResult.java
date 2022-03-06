package com.irme.server.webapp.graphql.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
public class SACategoryResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private int categroyId;
    private String name;
    private String description;

}