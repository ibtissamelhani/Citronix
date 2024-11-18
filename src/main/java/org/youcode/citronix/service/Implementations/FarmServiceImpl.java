package org.youcode.citronix.service.Implementations;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.youcode.citronix.domain.entities.Farm;
import org.youcode.citronix.repository.FarmRepository;
import org.youcode.citronix.service.FarmService;
import org.youcode.citronix.web.VM.Farm.FarmCreationVM;
import org.youcode.citronix.web.exception.Farm.InvalidFarmException;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FarmServiceImpl implements FarmService {

    private final FarmRepository farmRepository;

    @Override
    public Farm save(Farm farm) {
        if (farm == null){
            throw new InvalidFarmException("Farm object cannot be null.");
        }

        return farmRepository.save(farm);
    }

    @Override
    public List<Farm> getAllFarms() {
        return farmRepository.findAll();
    }

    @Override
    public Farm getFarmById(UUID id) {
        return null;
    }

    @Override
    public Farm updateFarm(UUID id, FarmCreationVM farmVM) {
        return null;
    }

    @Override
    public void deleteFarm(UUID id) {

    }

    @Override
    public Page<Farm> getFarmsWithPagination(int page, int size) {
        return null;
    }
}
