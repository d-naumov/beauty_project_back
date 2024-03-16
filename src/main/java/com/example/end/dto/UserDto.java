package com.example.end.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "UserDto", description = "Данные для регистрации")
public class UserDto {

    private Long id;

    private String roleName;

    @NotBlank(message = "Name is required")
    private String username;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Schema(description = "Email пользователя", example = "user@mail.com")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;


}

