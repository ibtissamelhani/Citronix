package org.youcode.citronix.service.DTO;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchFarmDTO {
    private String name;
    private String location;
    private LocalDate creationDate;
}
