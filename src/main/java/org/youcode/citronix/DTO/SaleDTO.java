package org.youcode.citronix.DTO;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleDTO {
    private UUID id;
    private LocalDate date;
    private double unitPrice;
    private String client;
    private double revenue;
}
