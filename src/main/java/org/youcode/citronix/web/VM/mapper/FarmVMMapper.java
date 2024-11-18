package org.youcode.citronix.web.VM.mapper;

import org.mapstruct.Mapper;
import org.youcode.citronix.domain.entities.Farm;
import org.youcode.citronix.web.VM.Farm.FarmVM;

@Mapper(componentModel = "spring")
public interface FarmVMMapper {
    Farm toFarm(FarmVM farmVM);
    FarmVM toFarmVM(Farm farm);
}
