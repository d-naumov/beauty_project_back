package com.example.end.service;

import com.example.end.dto.NewUserDto;
import com.example.end.dto.UserDto;
import com.example.end.exceptions.RestException;
import com.example.end.mail.ProjectMailSender;
import com.example.end.mapping.UserMapper;
import com.example.end.models.User;
import com.example.end.repository.UserRepository;
import com.example.end.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

//change
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;
    private final ProjectMailSender mailSender;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ProjectMailSender mailSender) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
    }

    @Override
    public UserDto register(NewUserDto newUserDto) {
        if (userRepository.existsByEmail(newUserDto.getEmail())) {
            throw new RestException(HttpStatus.CONFLICT,
                    "User with email <" + newUserDto.getEmail() + "> already exists");
        }
        User user = User.builder()
                .firstName(newUserDto.getFirstName())
                .lastName(newUserDto.getLastName())
                .email(newUserDto.getEmail())
                .role(newUserDto.getRole() != null ? newUserDto.getRole() : User.Role.CLIENT)
                .hashPassword(passwordEncoder.encode(newUserDto.getHashPassword()))
                .isActive(newUserDto.getRole() != User.Role.MASTER)
                .build();

        userRepository.save(user);

        if (newUserDto.getRole() != null && newUserDto.getRole().equals(User.Role.MASTER)) {
            String adminEmail = "admin@example.com";
            String subject = "Новый мастер ожидает подтверждения";
            String message = "Пользователь " + user.getLastName() + " ожидает подтверждения вашим администратором.";
            mailSender.sendEmail(adminEmail, subject, message);
        } else {
            String subject = "Регистрация на сайте";
            String message = "Поздравляем с успешной регистрацией на нашем сайте!";
            mailSender.sendEmail(user.getEmail(), subject, message);
        }
        return userMapper.toDto(user);
    }

    @Override
    public UserDto getById(Long id) {
        return userMapper.toDto(userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found for id: " + id)));
    }


    @Override
    public void confirmMaster(String masterUsername) {
        User masterUser = userRepository.findByFirstName(masterUsername);
        if (masterUser != null && masterUser.getRole() == User.Role.MASTER && !masterUser.isActive()) {
            masterUser.setActive(true);
            userRepository.save(masterUser);

            String subject = "Регистрация мастера подтверждена";
            String message = "Ваша регистрация в качестве мастера подтверждена. Теперь вы можете начать использовать наш сервис.";
            mailSender.sendEmail(masterUser.getEmail(), subject, message);

            String adminEmail = "admin@example.com";
            String adminSubject = "Регистрация мастера подтверждена";
            String adminMessage = "Регистрация мастера " + masterUsername + " успешно подтверждена.";
            mailSender.sendEmail(adminEmail, adminSubject, adminMessage);

            mailSender.sendMasterConfirmationRequest(masterUsername, masterUser.getLastName());
        } else {
            throw new IllegalArgumentException("Master user not found or already confirmed: " + masterUsername);
        }
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(x -> userMapper.toDto(x))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found for id: " + id));

        userRepository.delete(user);
    }
}

//    @Override
//    public UserRegistrationResponseDto register(UserRegistrationRequestDto registrationRequest) {
//
//        if(userRepository.existsByEmail(registrationRequest.getEmail())) {
//            throw new RestException(HttpStatus.CONFLICT,
//                    "User with email <" + registrationRequest.getEmail() + "> already exists");
//        }
//
//        User user = User.builder()
//                .firstName(registrationRequest.getFirstName())
//                .lastName(registrationRequest.getLastName())
//                .email(registrationRequest.getEmail())
//                .role(User.Role.USER)
//                .hashPassword(passwordEncoder.encode(registrationRequest.getPassword()))
//                .build();
//
//        user.setActive(true);
//       userRepository.save(user);
//
//       return userMapper.toRegistrationResponseDto(user);
//    }
