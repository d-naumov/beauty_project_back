package com.example.end.service.interfaces;


import com.example.end.dto.UserDto;
import com.example.end.models.User;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {
  User registerNewUser(UserDto userDto);

  Optional<User> findByUsername(String username);

  Optional<User> findByEmail(String email);

  List<User> getAllUsers();

  Optional<User> findById(Integer id);

  User getUserByUsername(String username);

  Optional<User> findById(int id);

  // void registerUser(UserDto userDto);

}



