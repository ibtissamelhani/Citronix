package org.youcode.citronix.service.Implementations;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.youcode.citronix.domain.entities.Farm;
import org.youcode.citronix.repository.FarmRepository;
import org.youcode.citronix.repository.FarmSearchRepository;
import org.youcode.citronix.service.DTO.SearchFarmDTO;
import org.youcode.citronix.service.FarmService;
import org.youcode.citronix.web.exception.Farm.FarmNotFoundException;
import org.youcode.citronix.web.exception.Farm.InvalidFarmException;
import org.youcode.citronix.web.exception.InvalidCredentialsException;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FarmServiceImpl implements FarmService {

    private final FarmRepository farmRepository;
    private final FarmSearchRepository farmSearchRepository;

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
    public Page<Farm> getFarmsWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return farmRepository.findAll(pageable);
    }

    @Override
    public Farm getFarmById(UUID id) {
        if (id==null){
            throw new InvalidCredentialsException("farm id is required");
        }
        return farmRepository.findById(id)
                .orElseThrow(()-> new FarmNotFoundException("farm not found"));
    }


    @Override
    public Farm updateFarm(UUID id, Farm farm) {
        if (id == null){
            throw new InvalidCredentialsException("farm id is required");
        }
        Farm farmToUpdate = this.getFarmById(id);
        if (farm == null){
            throw new InvalidFarmException("invalid farm");
        }
        farmToUpdate.setName(farm.getName());
        farmToUpdate.setArea(farm.getArea());
        farmToUpdate.setLocation(farm.getLocation());
        farmToUpdate.setCreationDate(farm.getCreationDate());
        return farmRepository.save(farmToUpdate);
    }

    @Override
    public void deleteFarm(UUID id) {
        if (id == null){
            throw new InvalidCredentialsException("farm id is required");
        }
        Farm farmToDelete = this.getFarmById(id);
        farmRepository.delete(farmToDelete);
    }

    @Override
    public List<Farm> search(SearchFarmDTO searchFarmDTO) {
        return farmSearchRepository.findByCriteria(searchFarmDTO);
    }
}
