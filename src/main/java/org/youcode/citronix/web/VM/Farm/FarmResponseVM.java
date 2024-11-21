package org.youcode.citronix.web.VM.Farm;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FarmResponseVM {
    private UUID id;
    private String name;
    private String location;
    private double area;
    private LocalDate creationDate;
    private int fieldSize;
}
