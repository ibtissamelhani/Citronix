package org.youcode.citronix.web.VM.Harvest;

import lombok.*;
import org.youcode.citronix.domain.entities.Harvest;
import org.youcode.citronix.domain.enums.Season;

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
}
