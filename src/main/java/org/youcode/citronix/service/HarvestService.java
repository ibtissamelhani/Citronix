package org.youcode.citronix.service;

import org.youcode.citronix.domain.entities.Harvest;

import java.util.UUID;

public interface HarvestService {

    Harvest createHarvest(UUID fieldId, Harvest harvest);
}
