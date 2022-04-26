package com.irme.common.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EvaluationReport {

    @Data
    public static class EvaluationReportItem {
        private int questionId;

        private int answerId;

        private String question;

        private String answer;

        private String description;
    }

    private List<EvaluationReportItem> items = new ArrayList<>();

    private int maxCategoryWeight;

    private int totalProcessWeight;

}