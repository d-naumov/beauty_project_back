package com.example.end.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO representing a role")
public class RoleDto {

    @Schema(description = "Unique identifier of the role", example = "1")
    private Long id;

    @Schema(description = "Name of the role", example = "ADMIN")
    private String name;

    @Schema(description = "Description of the role", example = "Administrator role")
    private String description;
}