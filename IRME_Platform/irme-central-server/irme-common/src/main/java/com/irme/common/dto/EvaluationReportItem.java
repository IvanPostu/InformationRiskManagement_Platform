package com.irme.common.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EvaluationReportItem implements Serializable {
    private int questionId;

    private int answerId;

    private String question;

    private String answer;

    private String description;
}