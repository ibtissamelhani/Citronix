package org.youcode.citronix.web.VM.Farm;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.youcode.citronix.domain.entities.Field;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FarmReqVM2 {

    @NotBlank(message = "name is required.")
    private String name;

    @NotBlank(message = "location is required.")
    private String location;

    @Positive(message = "Total area must be positive.")
    private double area;

    @NotNull(message = "Creation date is required.")
    private LocalDate creationDate;

    @NotEmpty(message = "fields are required required.")
    private List<Field> fields;
}
