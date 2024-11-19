package org.youcode.citronix.web.VM.mapper;

import org.mapstruct.Mapper;
import org.youcode.citronix.domain.entities.Farm;
import org.youcode.citronix.web.VM.Farm.FarmReqVM2;
import org.youcode.citronix.web.VM.Farm.FarmRequestVM;

@Mapper(componentModel = "spring")
public interface FarmReqVMMapper {

    Farm toFarm(FarmReqVM2 farmReqVM2);
    FarmReqVM2 toFarmReqVM2(Farm farm);
}
