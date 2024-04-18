package com.example.end.controller;

import com.example.end.controller.api.UserApi;
import com.example.end.dto.*;
import com.example.end.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
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
    public UserDto getMasterById(Long id) {
        return userService.getMasterById(id);
    }
    @Override
    public UserDto getClientById(Long id) {
        return userService.getClientById(id);
    }

    @Override
    public UserDto register(NewUserDto newUserDto) {
        return userService.register(newUserDto);
    }

    @Override
    public UserDetailsDto updateUserDetails(Long userId, NewUserDetailsDto userDetailsDto) {
        return userService.updateUserDetails(userId, userDetailsDto);
    }

    @Override
    public void confirmMasterByEmail(String email) {
      userService.confirmMasterByEmail(email);
    }


    @Override
    public List<UserDto> getAllMasters() {
        return userService.getAllMasters() ;
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