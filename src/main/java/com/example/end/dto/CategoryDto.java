package com.example.end.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {


    @Schema(description = "Unique identifier of the category", example = "1")
    private Long id;

    @NotBlank(message = "Category name cannot be blank")
    @Schema(description = "Name of the category", example = "Manicure")
    private String name;

}
