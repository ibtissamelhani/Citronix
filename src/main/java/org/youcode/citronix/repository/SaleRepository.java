package org.youcode.citronix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.youcode.citronix.domain.entities.Sale;

import java.util.List;
import java.util.UUID;

public interface SaleRepository extends JpaRepository<Sale, UUID> {
    List<Sale> findByHarvestId(UUID harvestId);
}
