package com.irme.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SACategoryDto {
    private int categroyId;
    private String name;
    private String description;
}
