package com.example.end.controller;

import com.example.end.dto.NewUserDto;
import com.example.end.dto.UserDto;
import com.example.end.service.interfaces.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(summary = "Authenticate user", description = "Authenticate a user with the provided email and password.")
    @PostMapping("/login")
    public ResponseEntity<UserDto> loginUser(@RequestBody NewUserDto newUserDto) {
        UserDto userDto = authenticationService.authenticate(newUserDto.getEmail(), newUserDto.getHashPassword());
        return ResponseEntity.ok(userDto);
    }
}

