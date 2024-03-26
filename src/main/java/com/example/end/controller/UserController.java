package com.example.end.controller;

import com.example.end.dto.NewUserDto;
import com.example.end.dto.UserDto;
import com.example.end.service.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Get user by ID", description = "Retrieve a user by their ID.")
    @GetMapping("/{id}")
    public UserDto getById(@Parameter(description = "ID of the user to be obtained. Cannot be empty.", required = true)
                           @PathVariable Long id) {
        return userService.getById(id);
    }

    @Operation(summary = "Register a new user", description = "Register a new user with the provided details.")
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "User details for registration.")
                                                @RequestBody NewUserDto newUserDto) {
        UserDto userDto = userService.register(newUserDto);
        return ResponseEntity.ok(userDto);
    }

    @Operation(summary = "Get all users", description = "Retrieve a list of all registered users.")
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Operation(summary = "Delete user by ID", description = "Delete a user by their ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@Parameter(description = "ID of the user to be deleted. Cannot be empty.", required = true)
                                           @PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
