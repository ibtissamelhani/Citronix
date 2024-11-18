package org.youcode.citronix.repository;

import org.youcode.citronix.domain.entities.Farm;
import org.youcode.citronix.DTO.Farm.SearchFarmDTO;

import java.util.List;

public interface FarmSearchRepository {
    List<Farm> findByCriteria(SearchFarmDTO searchFarmDTO);
}
