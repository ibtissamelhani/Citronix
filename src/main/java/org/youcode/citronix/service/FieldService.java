package org.youcode.citronix.service;

import org.springframework.data.domain.Page;
import org.youcode.citronix.DTO.Field.FieldRequestDTO;
import org.youcode.citronix.domain.entities.Field;

import java.util.UUID;

public interface FieldService {
    Field addField(FieldRequestDTO fieldRequestDTO);
    Field getFieldById(UUID id);
    void deleteField(UUID id);
    Page<Field> findAllByFarmId(UUID farmId, int page, int size);
}
