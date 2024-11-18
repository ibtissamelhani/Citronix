package org.youcode.citronix.web.VM.mapper;

import org.mapstruct.Mapper;
import org.youcode.citronix.domain.entities.Farm;
import org.youcode.citronix.web.VM.Farm.FarmRequestVM;

@Mapper(componentModel = "spring")
public interface FarmRequestVMMapper {
    Farm toFarm(FarmRequestVM farmRequestVM);
    FarmRequestVM toFarmCreationVM(Farm farm);
}
