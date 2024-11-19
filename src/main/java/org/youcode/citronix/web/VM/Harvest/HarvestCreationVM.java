package org.youcode.citronix.web.VM.Harvest;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.youcode.citronix.domain.enums.Season;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HarvestCreationVM {
    @NotNull(message = "Season is required.")
    private Season season;

    @NotNull(message = "Date is required.")
    private LocalDate date;
}
