package com.irme.server.validation;

import com.irme.server.business_entities.BusinessLogicEntity;
import java.util.Optional;

@FunctionalInterface
public interface ValidationStrategy<T extends BusinessLogicEntity> {
    Optional<String> validate(T businessLogicEntity);
}
