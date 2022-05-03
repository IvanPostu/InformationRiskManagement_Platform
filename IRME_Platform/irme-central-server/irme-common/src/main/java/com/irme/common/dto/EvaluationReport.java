package com.irme.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class EvaluationReport implements Serializable {

    private List<EvaluationReportItem> items = new ArrayList<>();

    private int maxCategoryWeight;

    private int totalProcessWeight;

    private int expectedProcessWeight;

}