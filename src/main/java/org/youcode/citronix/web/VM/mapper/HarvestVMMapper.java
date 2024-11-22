package org.youcode.citronix.web.VM.mapper;

import org.mapstruct.Mapper;
import org.youcode.citronix.domain.entities.Harvest;
import org.youcode.citronix.web.VM.Harvest.HarvestCreationVM;
import org.youcode.citronix.web.VM.Harvest.HarvestResponseVM;

@Mapper(componentModel = "spring")
public interface HarvestVMMapper {
    Harvest toHarvest(HarvestCreationVM harvestCreationVM);
    HarvestResponseVM toHarvestResponseVM(Harvest harvest);
}
