package com.example.end.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO representing a user")
public class UserDto {

    @Schema(description = "Unique identifier of the user", example = "1")
    private Long id;

    @NotBlank(message = "First name cannot be blank")
    @Pattern(regexp = "[A-Z][a-z]{3,}", message = "First name must start with an uppercase letter and contain at least 3 characters")
    @Schema(description = "First name of the user", example = "John")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Pattern(regexp = "[A-Z][a-z]{3,}", message = "Last name must start with an uppercase letter and contain at least 3 characters")
    @Schema(description = "Last name of the user", example = "Doe")
    private String lastName;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    @Schema(description = "Email address of the user", example = "john.doe@example.com")
    private String email;

    @Schema(description = "Flag indicating whether the user is active", example = "true")
    private boolean isActive;

    @Schema(description = "Hashed password of the user")
    private String hashPassword;

    @Schema(description = "Description of the user")
    private String description;

    @Schema(description = "Set of roles assigned to the user")
    private Set<RoleDto> roles;

    @Schema(description = "List of reviews given by the user")
    private List<ReviewDto> reviews;

    @Schema(description = "Set of categories associated with the user")
    private Set<CategoryDto> categories;

    @Schema(description = "Set of procedures associated with the user")
    private Set<ProcedureDto> procedures;

    @Schema(description = "Cart associated with the user")
    private CartDto cart;

}

