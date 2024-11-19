package org.youcode.citronix.service.Implementations;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.youcode.citronix.DTO.Farm.SearchFarmDTO;
import org.youcode.citronix.domain.entities.Farm;
import org.youcode.citronix.domain.entities.Field;
import org.youcode.citronix.repository.FarmRepository;
import org.youcode.citronix.service.FarmService;
import org.youcode.citronix.service.FieldService;
import org.youcode.citronix.web.exception.Farm.InvalidFarmException;

import java.util.List;
import java.util.UUID;

@Service("farmServiceImpl2")
@AllArgsConstructor
public class FarmServiceImpl2 implements FarmService {

    private final FarmRepository farmRepository;
    private final FieldService fieldService;


    @Transactional
    @Override
    public Farm save(Farm farm) {
        if (farm == null){
            throw new InvalidFarmException("Farm object cannot be null.");
        }
        Farm savedFarm = farmRepository.save(farm);
        farm.getFields().forEach(field -> {
            field.setFarm(savedFarm);
            fieldService.save(field);
        });
        return savedFarm;
    }

    public List<Farm> getFarmsWithArea() {
        List<Farm> allFarms = getAllFarms();
       return allFarms.stream().filter(farm -> farm.getFields().stream()
                    .mapToDouble(Field::getArea)
                    .sum() < 0.4
        ).toList();
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
    public Farm updateFarm(UUID id, Farm farm) {
        return null;
    }

    @Override
    public void deleteFarm(UUID id) {

    }

    @Override
    public Page<Farm> getFarmsWithPagination(int page, int size) {
        return null;
    }

    @Override
    public List<Farm> search(SearchFarmDTO searchFarmDTO) {
        return List.of();
    }
}
