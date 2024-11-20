package org.youcode.citronix.service;

import org.youcode.citronix.domain.entities.Harvest;
import org.youcode.citronix.domain.enums.Season;

import java.util.List;
import java.util.UUID;

public interface HarvestService {

    Harvest createHarvest(UUID fieldId, Harvest harvest);
    Harvest findById(UUID id);
    void delete(UUID id);
    List<Harvest> getHarvestsBySeason(Season season);
}
