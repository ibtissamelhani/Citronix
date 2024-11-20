package org.youcode.citronix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.youcode.citronix.domain.entities.HarvestDetail;
import org.youcode.citronix.domain.entities.Tree;
import org.youcode.citronix.domain.enums.Season;

import java.util.UUID;

public interface HarvestDetailRepository extends JpaRepository<HarvestDetail, UUID> {
    boolean existsByTreeAndHarvestSeason(Tree tree, Season season);
}
