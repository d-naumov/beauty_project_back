package com.example.end.controller;

import com.example.end.dto.LoginRequestDto;
import com.example.end.security.sec_dto.RefreshRequestDto;
import com.example.end.security.sec_dto.TokenResponseDto;
import com.example.end.service.interfaces.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.security.auth.message.AuthException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(summary = "Authenticate user", description = "Authenticate a user with the provided email and password.")
    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody @Valid LoginRequestDto loginRequest, HttpServletResponse response) throws AuthException {
        TokenResponseDto tokenDto = authenticationService.login(loginRequest);
        Cookie cookie = new Cookie("Access-Token", tokenDto.getAccessToken());
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/access")
    public ResponseEntity<TokenResponseDto> getNewAccessToken(@RequestBody RefreshRequestDto request) {
        TokenResponseDto accessToken = authenticationService.getAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(accessToken);
    }

    // При логауте куке с токеном доступа выставляется время жизни, равное 0,
    // благодаря чему данная кука сразу же перестаёт действовать.
    @GetMapping("/logout")
    public void logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("Access-Token", null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

}

