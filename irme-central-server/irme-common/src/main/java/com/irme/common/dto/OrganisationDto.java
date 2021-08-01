package com.irme.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrganisationDto {

    private Integer id;

    private String name;

    private String description;

    private Date created;

    private String base64ImageLogo;

}
