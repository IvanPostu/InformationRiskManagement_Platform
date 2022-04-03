package com.irme.server.webapp.graphql.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class SAAnswerResult implements Serializable {

    private static final long serialVersionUID = 2L;

    private int answerId;

    private String answer;

}
