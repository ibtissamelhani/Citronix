package org.youcode.citronix.service.Implementations;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.youcode.citronix.domain.entities.Farm;
import org.youcode.citronix.domain.entities.Field;
import org.youcode.citronix.repository.FieldRepository;
import org.youcode.citronix.service.FarmService;
import org.youcode.citronix.service.FieldService;

import java.util.UUID;

@Service
@AllArgsConstructor
public class FieldServiceImpl implements FieldService {

    private final FieldRepository fieldRepository;
    private final FarmService farmService;

    @Override
    public Field addField(UUID farmId, Field field) {
        Farm farm = farmService.getFarmById(farmId);
        field.setFarm(farm);
        farm.getFields().add(field);
        return fieldRepository.save(field);
    }
}
