package com.irme.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SAQuestionWithAnswers {
    private int questionId;

    private int parentQuestionId;

    private String question;

    private boolean hasMultipleAnswers;

    private int questionWeight;

    private List<SAAnswerDto> answers;
}
