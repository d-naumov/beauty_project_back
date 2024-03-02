package com.example.end.service.interfaces;


import com.example.end.dto.AdminDto;
import com.example.end.dto.UserDto;
import com.example.end.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
  User registerNewUser(UserDto userDto);
  Optional<User> findByUsername(String username);
  Optional<User> findByEmail(String email);
  List<User> getAllUsers();







}


