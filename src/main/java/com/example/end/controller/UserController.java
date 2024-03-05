package com.example.end.controller;


import com.example.end.dto.UserDto;
import com.example.end.entity.User;
import com.example.end.service.interfaces.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//  контроллер с Swagger-аннотациями
@RestController
@RequestMapping("/api/users")
@Api(tags = "Users", description = "Operations related to users")
public class UserController {
  private  UserService userService;

  @GetMapping("/{id}")
  @ApiOperation(value = "Get user by ID", response = User.class)
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
    return null;
    // Логика метода
  }

  @PostMapping
  @ApiOperation(value = "Create a new user", response = User.class)
  public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
    return null;
    // Логика метода
  }

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto,
                                        BindingResult bindingResult) {
    // Метод для обработки регистрации нового пользователя

    if (bindingResult.hasErrors()) {
      // Обработка ошибок валидации
      return ResponseEntity.badRequest().body("Ошибка валидации");
    }

    // Продолжение логики регистрации пользователя
    try {
      // Создание пользователя
      User registeredUser = userService.registerUser(userDto);

      // Отправка подтверждения на электронную почту (здесь необходимо реализовать соответствующий сервис)

      return ResponseEntity.ok("Пользователь успешно зарегистрирован");
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка регистрации пользователя");
    }
  }

  @GetMapping("/{username}")
  public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
    return null;
    // Метод для получения информации о пользователе по имени пользователя
  }
}

