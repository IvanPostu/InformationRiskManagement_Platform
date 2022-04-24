package com.irme.common.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EvaluationResult {

    private int processId;

    private int categoryId;

    private Date created;

    private int answerTotalWeight;

    private int answerMaxWeight;

    private int statusCode;

}
