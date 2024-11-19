package org.youcode.citronix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.youcode.citronix.domain.entities.Field;
import org.youcode.citronix.domain.entities.Harvest;
import org.youcode.citronix.domain.enums.Season;

import java.util.UUID;

public interface HarvestRepository extends JpaRepository<Harvest, UUID> {

    boolean existsByFieldAndSeason(Field field, Season season);
}
