package org.youcode.citronix.service.Implementations;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.youcode.citronix.DTO.Field.FieldRequestDTO;
import org.youcode.citronix.domain.entities.Farm;
import org.youcode.citronix.domain.entities.Field;
import org.youcode.citronix.repository.FieldRepository;
import org.youcode.citronix.service.FarmService;
import org.youcode.citronix.service.FieldService;
import org.youcode.citronix.web.exception.Farm.FarmSizeException;
import org.youcode.citronix.web.exception.InvalidCredentialsException;

import java.util.UUID;

@Service
@AllArgsConstructor
public class FieldServiceImpl implements FieldService {

    private final FieldRepository fieldRepository;
    private final FarmService farmService;

    @Transactional
    @Override
    public Field addField(FieldRequestDTO fieldRequestDTO) {
        Farm farm = farmService.getFarmById(fieldRequestDTO.getFarmId());

        validateField(farm,fieldRequestDTO.getArea());

        Field field = Field.builder()
                .area(fieldRequestDTO.getArea())
                .farm(farm)
                .build();

        farm.getFields().add(field);

        return fieldRepository.save(field);
    }

    private void validateField(Farm farm, Double newFieldArea) {
        if (newFieldArea < 0.1) {
            throw new InvalidCredentialsException("Field area must be at least 0.1 hectares (1,000 m²)");
        }

        if (newFieldArea > (0.5 * farm.getArea())) {
            throw new InvalidCredentialsException("Field area cannot exceed 50% of the farm's total area");
        }

        if (farm.getFields().size() >= 10) {
            throw new FarmSizeException("A farm cannot have more than 10 fields");
        }

        double totalArea = farm.getFields().stream()
                .mapToDouble(Field::getArea)
                .sum() + newFieldArea;
        if (totalArea >= farm.getArea()) {
            throw new FarmSizeException("Total field area must be less than the farm's total area");
        }
    }
}
