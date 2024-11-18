package org.youcode.citronix.service.Implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.youcode.citronix.domain.entities.Farm;
import org.youcode.citronix.repository.FarmRepository;
import org.youcode.citronix.service.FarmService;
import org.youcode.citronix.web.exception.Farm.InvalidFarmException;

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
}
