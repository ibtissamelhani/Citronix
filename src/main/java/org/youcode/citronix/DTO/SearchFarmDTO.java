package org.youcode.citronix.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchFarmDTO {
    private String name;
    private String location;
}
