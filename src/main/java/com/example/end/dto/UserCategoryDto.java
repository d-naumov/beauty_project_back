package com.example.end.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCategoryDto {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Unique identifier of the master", example = "1")
    private Long id;

    @Schema(description = "First name of the master", example = "John")
    private String firstName;

    @Schema(description = "Last name of the master", example = "Doe")
    private String lastName;

    @Schema(description = "Phone number of the master", example = "+1234567890")
    private String address;

    @Schema(description = "List of categories the master is associated with")
    private List<CategoryDto> categories;

}
