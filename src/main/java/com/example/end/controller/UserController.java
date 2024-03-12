package com.example.end.controller;


import com.example.end.models.dto.UserDto;
import com.example.end.models.User;
import com.example.end.service.interfaces.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

//  контроллер с Swagger-аннотациями
@RestController
@RequestMapping("/api/users")
@Api(tags = "Users", description = "Operations related to users")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }
}

//  @GetMapping("/{id}")
//  @ApiOperation(value = "Get user by ID", response = User.class)
//  public ResponseEntity<User> getUserById(@PathVariable Long id) {
//    Optional<User> userOptional = userService.findById(id);
//    return userOptional.map(user -> ResponseEntity.ok().body(user))
//            .orElseGet(() -> ResponseEntity.notFound().build());
//  }
//
//  @PostMapping
//  @ApiOperation(value = "Create a new user", response = User.class)
//  public ResponseEntity<User> createUser(@Valid @RequestBody UserDto userDto) {
//    User createdUser = userService.registerNewUser(userDto);
//    return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
//  }

//  @PostMapping("/register")
//  public ResponseEntity<String> registerUser(@Valid @RequestBody UserDto userDto,
//                                             BindingResult bindingResult) {
//    if (bindingResult.hasErrors()) {
//      return ResponseEntity.badRequest().body("Ошибка валидации");
//    }
//
//    try {
//      userService.registerNewUser(userDto);
//      return ResponseEntity.ok("Пользователь успешно зарегистрирован");
//    } catch (Exception e) {
//      e.printStackTrace();
//      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//              .body("Ошибка регистрации пользователя");
//    }
//  }

//  @GetMapping("/{username}")
//  public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
//    Optional<User> userOptional = userService.findByUsername(username);
//    return userOptional.map(user -> ResponseEntity.ok().body(user))
//            .orElseGet(() -> ResponseEntity.notFound().build());
//  }
//}

