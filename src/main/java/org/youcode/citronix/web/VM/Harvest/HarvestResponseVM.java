package org.youcode.citronix.web.VM.Harvest;

import lombok.*;
import org.youcode.citronix.domain.entities.Harvest;
import org.youcode.citronix.domain.enums.Season;
import org.youcode.citronix.web.VM.Field.FieldVM;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HarvestResponseVM {
    private UUID id;
    private Season season;
    private LocalDate date;
    private double totalQuantity;

    public static HarvestResponseVM fromEntity(Harvest harvest) {
        HarvestResponseVM vm = new HarvestResponseVM();
        vm.setId(harvest.getId());
        vm.setSeason(harvest.getSeason());
        vm.setDate(harvest.getDate());
        vm.setTotalQuantity(harvest.getTotalQuantity());
        return vm;
    }
}
