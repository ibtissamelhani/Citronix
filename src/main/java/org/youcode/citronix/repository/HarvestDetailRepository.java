package org.youcode.citronix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.youcode.citronix.domain.entities.HarvestDetail;

import java.util.UUID;

public interface HarvestDetailRepository extends JpaRepository<HarvestDetail, UUID> {
}
