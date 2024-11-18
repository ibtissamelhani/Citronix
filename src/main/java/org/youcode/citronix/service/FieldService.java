package org.youcode.citronix.service;

import org.youcode.citronix.domain.entities.Field;

import java.util.UUID;

public interface FieldService {
    Field addField(UUID farmId, Field field);
}
