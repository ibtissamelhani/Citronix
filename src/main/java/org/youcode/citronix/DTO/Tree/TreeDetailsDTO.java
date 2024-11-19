package org.youcode.citronix.DTO.Tree;

import lombok.*;
import org.youcode.citronix.domain.entities.Field;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TreeDetailsDTO {
    private LocalDate plantingDate;
    private int age;
    private double productivity;
    private Field fieldId;
}
