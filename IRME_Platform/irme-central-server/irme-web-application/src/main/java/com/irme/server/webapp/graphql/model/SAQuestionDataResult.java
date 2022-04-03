package com.irme.server.webapp.graphql.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode
public class SAQuestionDataResult implements Serializable {

    private static final long serialVersionUID = 2L;

    private int questionId;

    private int parentQuestionId;

    private List<SAAnswerResult> answers;

    private String question;

    private boolean hasMultipleAnswers;

    private int questionWeight;

}
