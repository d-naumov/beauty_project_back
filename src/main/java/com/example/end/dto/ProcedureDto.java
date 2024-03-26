package com.example.end.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcedureDto {

    @Schema(description = "Unique identifier of the procedure", example = "1")
    private Long id;

    @NotBlank(message = "Procedure name cannot be blank")
    @Pattern(regexp = "[A-Z][a-z]{3,}", message = "Procedure name must start with an uppercase letter and contain at least 3 characters")
    @Schema(description = "Name of the procedure", example = "Haircut")
    private String name;


}

