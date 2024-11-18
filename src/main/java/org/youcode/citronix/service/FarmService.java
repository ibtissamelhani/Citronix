package org.youcode.citronix.service;

import org.springframework.data.domain.Page;
import org.youcode.citronix.domain.entities.Farm;
import org.youcode.citronix.web.VM.Farm.FarmCreationVM;

import java.util.List;
import java.util.UUID;

public interface FarmService {
    Farm save(Farm farm);
    List<Farm> getAllFarms();
    Farm getFarmById(UUID id);
    Farm updateFarm(UUID id, FarmCreationVM farmVM);
    void deleteFarm(UUID id);
    Page<Farm> getFarmsWithPagination(int page, int size);
}