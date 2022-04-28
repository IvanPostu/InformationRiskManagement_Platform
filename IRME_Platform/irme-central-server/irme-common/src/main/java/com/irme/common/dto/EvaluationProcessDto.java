package com.irme.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * This dto is used to: extract data from db result set
 * AND in order to map graphql result.
 * 
 */
@Data
@EqualsAndHashCode
public class EvaluationProcessDto implements Serializable {

    private int processId;

    private int organisationId;

    private String organisationName;

    private Date created;

    private String status;

    private int statusCode;

    private int userId;

    private String userEmail;

    private int categoryId;

    private String categoryName;

}
