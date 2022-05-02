package com.irme.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class SAProcessAnsweredQuestion implements Serializable {
    private int questionAnswerId;
    private int questionId;
    private int answerId;
}
