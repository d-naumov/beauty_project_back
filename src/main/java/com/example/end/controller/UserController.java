package com.example.end.controller;


import com.example.end.dto.UserDto;
import com.example.end.models.User;
import com.example.end.service.interfaces.UserService;

import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.Optional;

//  контроллер с Swagger-аннотациями
@RestController
@RequestMapping("/api/users")

public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/id/{id}")

    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

//    @Operation(summary = "registration", description = "blabla")
//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@Valid @RequestBody UserDto userDto,
//                                               BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return ResponseEntity.badRequest().body("Ошибка валидации");
//        }
//
//        try {
//            // Определяем роль пользователя
//            String role = userDto.getRoleName();
//            if (role == null || role.isEmpty()) {
//                userDto.setRoleName("CLIENT"); // Если роль не указана, считаем, что это пользователь
//            }
//
//            userService.registerNewUser(userDto);
//            return ResponseEntity.ok("Пользователь успешно зарегистрирован");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Ошибка регистрации пользователя");
//        }
//    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        Optional<User> userOptional = userService.findByUsername(username);
        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

