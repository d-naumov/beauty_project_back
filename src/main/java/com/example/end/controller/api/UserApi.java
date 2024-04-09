package com.example.end.controller.api;

import com.example.end.dto.NewUserDto;
import com.example.end.dto.StandardResponseDto;
import com.example.end.dto.UserDto;
import com.example.end.validation.dto.ValidationErrorsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tags(value = {
        @Tag(name = "Users", description = "Operations related to users")
})
@RequestMapping("/api/users")
public interface UserApi {
    @Operation(summary = "Get user by ID", description = "Retrieve a user by their ID.")
    @GetMapping("/{id}")
    UserDto getById(@Parameter(description = "ID of the user to be obtained. Cannot be empty.", required = true)
                    @PathVariable Long id);

    @Operation(summary = "Register a new user", description = "Available to everyone. By default, the role is CLIENT.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "User was registered successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Validation error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorsDto.class))),
            @ApiResponse(responseCode = "409",
                    description = "User with such email already exists",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponseDto.class)))
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    UserDto register(@RequestBody @Valid NewUserDto newUserDto);


    @Operation(summary = "Confirm master by email", description = "Available to ADMIN")
    @PostMapping("/confirm")
    void confirmMasterByEmail(@RequestParam String email);

//    @Operation(summary = "Authenticate user", description = "Authenticate a user with the provided email and password.")
//    @PostMapping("/login")
//    UserDto loginUser(@RequestParam String email,
//                                      @RequestParam String password);

    @Operation(summary = "Get all users", description = "Available to ADMIN")
    @GetMapping()
    List<UserDto> getAllUsers();

    @Operation(summary = "Get all usersMasters", description = "Available to ADMIN")
    @GetMapping("/masters")
    List<UserDto> getAllMasters();

    @Operation(summary = "Delete user by ID", description = "Available to ADMIN")
    @DeleteMapping("/{id}")
    void deleteById(@Parameter(description = "ID of the user to be deleted. Cannot be empty.", required = true)
                                    @PathVariable Long id);
}
