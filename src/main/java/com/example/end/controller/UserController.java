package com.example.end.controller;

import com.example.end.controller.api.UserApi;
import com.example.end.dto.NewUserDto;
import com.example.end.dto.UserDto;
import com.example.end.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController implements UserApi {

    private final UserService userService;

    @Override
    public UserDto getById(Long id) {
        return userService.getById(id);
    }

    @Override
    public UserDto register(NewUserDto newUserDto) {
        return userService.register(newUserDto);
    }

    @Override
    public void confirmMasterByEmail(String email) {
      userService.confirmMasterByEmail(email);
    }

    @Override
    public UserDto loginUser(String email, String password) {
        return userService.authenticate(email, password);
    }
    @GetMapping("/masters")
    public ResponseEntity<List<UserDto>> getAllMasters() {
        List<UserDto> masters = userService.getAllMasters();
        return new ResponseEntity<>(masters, HttpStatus.OK);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    public void deleteById(Long id) {
       userService.deleteById(id);
    }
}