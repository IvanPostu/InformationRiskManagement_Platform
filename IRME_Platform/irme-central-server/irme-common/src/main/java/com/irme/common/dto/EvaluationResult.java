package com.irme.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * This dto is used to: extract data from db result set
 * AND in order to map graphql result.
 * 
 */
@Data
public class EvaluationResult implements Serializable {

    private int processId;

    private int categoryId;

    private Date created;

    private int answerTotalWeight;

    private int answerMaxWeight;

    private int statusCode;

}
