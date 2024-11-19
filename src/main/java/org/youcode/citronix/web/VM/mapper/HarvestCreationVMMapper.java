package org.youcode.citronix.web.VM.mapper;

import org.mapstruct.Mapper;
import org.youcode.citronix.domain.entities.Harvest;
import org.youcode.citronix.web.VM.Harvest.HarvestCreationVM;

@Mapper(componentModel = "spring")
public interface HarvestCreationVMMapper {
    Harvest toHarvest(HarvestCreationVM harvestCreationVM);
    HarvestCreationVM toHarvestCreationVM(Harvest harvest);
}
