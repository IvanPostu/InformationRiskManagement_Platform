package com.irme.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CountryDto {
    private String countryCode;
    private String countryName;
}
