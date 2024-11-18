package org.youcode.citronix.DTO.Field;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FieldRequestDTO {

    @Positive(message = "Field area must be positive.")
    private double area;
    @NotNull(message = "farm id is required")
    private UUID farmId;
}
