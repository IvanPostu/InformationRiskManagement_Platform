package com.irme.server.validation;

import com.irme.server.business_entities.BusinessLogicEntity;

public interface ValidationFactory<T extends BusinessLogicEntity> {
    ValidationStrategy<T> getValidationCondition();
}
