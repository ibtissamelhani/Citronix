package org.youcode.citronix.web.VM.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.youcode.citronix.domain.entities.Farm;
import org.youcode.citronix.domain.entities.Field;
import org.youcode.citronix.web.VM.Farm.FarmRequestVM;
import org.youcode.citronix.web.VM.Farm.FarmResponseVM;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FarmVMMapper {
    Farm toFarm(FarmRequestVM farmRequestVM);

    @Mapping(source = "fields", target = "fieldSize", qualifiedByName = "calculateFieldSize")
    FarmResponseVM toFarmResponseVM(Farm farm);

    @Named("calculateFieldSize")
    default int calculateFieldSize(List<Field> fields) {
        return fields == null ? 0 : fields.size();
    }
}
