package org.youcode.citronix.DTO.Tree;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TreeDetailsDTO {
    private LocalDate plantingDate;
    private int age;
    private double productivity;
}
