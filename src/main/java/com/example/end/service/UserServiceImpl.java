package com.example.end.service;


import com.example.end.dto.UserDto;
import com.example.end.entity.User;
import com.example.end.repository.interfaces.UserRepository;
import com.example.end.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Override
    public User registerNewUser(UserDto userDto) {
        // Реализация создания нового пользователя
        User newUser = new User();
        newUser.setUsername(userDto.getUsername());
        newUser.setEmail(userDto.getEmail());
        // Добавьте логику для установки пароля, ролей и других полей

        return userRepository.save(newUser);
    }
    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    // Другие методы реализации
}

