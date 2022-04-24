package com.irme.common.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EvaluationProcessDto {

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
