package org.youcode.citronix.web.VM.Farm;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class FarmCreationVM {

    @NotBlank(message = "name is required.")
    private String name;

    @NotBlank(message = "location is required.")
    private String location;

    @Positive(message = "Total area must be positive.")
    private double area;

    @NotNull(message = "Creation date is required.")
    private LocalDate creationDate;
}
