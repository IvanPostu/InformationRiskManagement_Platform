package com.irme.common.dto;

import lombok.Data;

@Data
public class EvaluationReport {

    private int questionId;

    private int answerId;

    private String question;

    private String answer;

    private String description;

}