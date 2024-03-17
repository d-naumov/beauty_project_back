package com.example.end.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcedureDto {

    @Schema(description = "Unique identifier of the procedure", example = "1")
    private Long id;

    @NotBlank(message = "Procedure name cannot be blank")
    @Pattern(regexp = "[A-Z][a-z]{3,}", message = "Procedure name must start with an uppercase letter and contain at least 3 characters")
    @Schema(description = "Name of the procedure", example = "Haircut")
    private String name;

    @Schema(description = "Price of the procedure", example = "50.0")
    @Max(value = 90000, message = "Price cannot exceed 90000")
    @Min(value = 10, message = "Price must be at least 10")
    private double price;

    @Schema(description = "Flag indicating whether the procedure is active", example = "true")
    private boolean isActive;

}

