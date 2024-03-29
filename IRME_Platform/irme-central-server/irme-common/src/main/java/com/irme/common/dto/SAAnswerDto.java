package com.irme.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SAAnswerDto implements Serializable {

    private int answerId;

    private int questionAnswerId;

    private String answer;
}
