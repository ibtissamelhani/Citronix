package org.youcode.citronix.service.Implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.youcode.citronix.domain.entities.Field;
import org.youcode.citronix.domain.entities.Harvest;
import org.youcode.citronix.repository.HarvestRepository;
import org.youcode.citronix.service.FieldService;
import org.youcode.citronix.service.HarvestService;

import java.util.UUID;

@Service
@AllArgsConstructor
public class HarvestServiceImpl implements HarvestService {

    private final HarvestRepository harvestRepository;
    private final FieldService fieldService;

    @Override
    public Harvest createHarvest(UUID fieldId, Harvest harvest) {

        Field fieldToHarvest = fieldService.getFieldById(fieldId);

    }
}
