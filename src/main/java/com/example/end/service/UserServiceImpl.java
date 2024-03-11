package com.example.end.service;


import com.example.end.dto.UserDto;
import com.example.end.entity.Role;
import com.example.end.entity.User;
import com.example.end.repository.interfaces.UserRepository;
import com.example.end.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CartService cartService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, CartService cartService) {
        this.userRepository = userRepository;
        this.cartService = cartService;
    }

    @Override
    public User registerNewUser(UserDto userDto) {
        // Проверка, существует ли пользователь с таким адресом электронной почты
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new RuntimeException("Email is already taken");
        }

        User newUser = new User();
        newUser.setFirstName(userDto.getFirstName());
        newUser.setLastName(userDto.getLastName());
        newUser.setEmail(userDto.getEmail());

        // Логика установки пароля (вы можете использовать, например, BCryptPasswordEncoder)
        // newUser.setHashPassword(passwordEncoder.encode(userDto.getPassword()));

        // Логика установки ролей (в данном примере, устанавливаем роль USER)
        Role userRole = new Role();
        userRole.setName("USER");
        newUser.getRoles().add(userRole);

        // Сохранение нового пользователя в репозитории
        return userRepository.save(newUser);
    }

    @Override
    public Optional<User> findByUsername(String firstname) {
        return Optional.ofNullable(userRepository.findByFirstName(firstname));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByUsername(String firstname) {
        return userRepository.findByFirstName(firstname);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }


    @Override
    public UserDetails loadUserByUsername(String firstname) throws UsernameNotFoundException {
        User user = userRepository.findByFirstName(firstname);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + firstname);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getFirstName(), user.getHashPassword(), user.isActive(),
                true, true, true,
                getAuthorities(user.getRoles())
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toList());
    }
}


