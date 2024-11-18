package org.youcode.citronix.web.VM.mapper;

import org.mapstruct.Mapper;
import org.youcode.citronix.domain.entities.Farm;
import org.youcode.citronix.web.VM.Farm.FarmCreationVM;

@Mapper(componentModel = "spring")
public interface FarmCreationVMMapper {
    Farm toFarm(FarmCreationVM farmCreationVM);
    FarmCreationVM toFarmCreationVM(Farm farm);
}
