package org.youcode.citronix.repository;

import org.springframework.stereotype.Repository;
import org.youcode.citronix.domain.entities.Farm;
import org.youcode.citronix.service.DTO.SearchFarmDTO;

import java.util.List;

public interface FarmSearchRepository {
    List<Farm> findByCriteria(SearchFarmDTO searchFarmDTO);
}
