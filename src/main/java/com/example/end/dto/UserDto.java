package com.example.end.dto;


import com.example.end.models.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO representing a user")
public class UserDto {

    @Schema(description = "Unique identifier of the user", example = "1")
    private Long id;


    @Schema(description = "First name of the user", example = "John")
    private String firstName;


    @Schema(description = "Last name of the user", example = "Doe")
    private String lastName;


    @Schema(description = "Email address of the user", example = "john.doe@example.com")
    private String email;

    @Schema(description = "Set of roles assigned to the user")
    private User.Role role;

//    @Schema(description = "List of reviews given by the user")
//    private List<ReviewDto> reviews;
//
//    @Schema(description = "Set of categories associated with the user")
//    private Set<CategoryDto> categories;
//
//    @Schema(description = "Set of procedures associated with the user")
//    private Set<ProcedureDto> procedures;
//
//    @Schema(description = "Cart associated with the user")
//    private CartDto cart;
public static UserDto from(User user) {
    return UserDto.builder()
            .id(user.getId())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .role(User.Role.valueOf(user.getRole().toString()))
            .build();
}

    public static List<UserDto> from(Collection<User> users) {
        return users.stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }
}

