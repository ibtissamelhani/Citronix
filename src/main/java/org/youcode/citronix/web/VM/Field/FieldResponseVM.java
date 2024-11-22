package org.youcode.citronix.web.VM.Field;

import lombok.*;
import org.youcode.citronix.domain.entities.Field;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FieldResponseVM {
    private UUID id ;
    private double area;
    private String farmName;
    private String farmLocation;
}
