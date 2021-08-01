package com.irme.common.dto;

import lombok.Getter;
import lombok.Setter;

public class OrganisationIncludingRelatedToTheUserDto extends OrganisationDto {

    @Setter
    @Getter
    private boolean relatedToTheUserDto;

}
