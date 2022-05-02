package com.irme.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class SAQuestionWithAnswers {
    private int questionId;

    private int parentQuestionAnswerId;

    private String question;

    private boolean hasMultipleAnswers;

    private int questionWeight;

    private List<SAAnswerDto> answers = new ArrayList<>();
}
