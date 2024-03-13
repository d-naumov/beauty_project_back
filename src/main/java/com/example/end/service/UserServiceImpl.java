//package com.example.end.service;
//
//
//import com.example.end.dto.UserDto;
//import com.example.end.models.User;
//import com.example.end.repository.UserRepository;
//import com.example.end.service.interfaces.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    private final UserRepository userRepository;
//    private final CartServiceImpl cartService;
//
//    @Autowired
//    public UserServiceImpl(UserRepository userRepository, CartServiceImpl cartService) {
//        this.userRepository = userRepository;
//        this.cartService = cartService;
//    }
//
//
//
//    @Override
//    public User registerNewUser(UserDto userDto) {
//        User newUser = new User();
//        newUser.setUsername(userDto.getUsername());
//        newUser.setEmail(userDto.getEmail());
//        // Добавьте логику для установки пароля, ролей и других полей
//
//        // Сохранение нового пользователя в репозитории
//        return userRepository.save(newUser);
//    }
//    @Override
//    public Optional<User> findByUsername(String username) {
//        return Optional.ofNullable(userRepository.findByUsername(username));
//    }
//
//    @Override
//    public Optional<User> findByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }
//
//    @Override
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//    @Override
//    public User getUserByUsername(String username) {
//        return userRepository.findByUsername(username);
//
//    }
//    @Override
//    public User registerUser(UserDto userDto) {
//        // Проверка, существует ли пользователь с таким именем пользователя
//        if (userRepository.existsByUsername(userDto.getUsername())) {
//            throw new RuntimeException("Username is already taken");
//        }
//
//        // Проверка, существует ли пользователь с таким адресом электронной почты
//        if (userRepository.existsByEmail(userDto.getEmail())) {
//            throw new RuntimeException("Email is already taken");
//        }
//
//        // Создание и сохранение нового пользователя
//        User newUser = new User();
//        newUser.setUsername(userDto.getUsername());
//        newUser.setEmail(userDto.getEmail());
//        // Добавьте логику для установки пароля, ролей и других полей
//        return userRepository.save(newUser);
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//
//        return null;


//        return new org.springframework.security.core.userdetails.User(
////                user.getUsername(), user.getPassword(), new ArrayList<>());
//    }
//
//}

