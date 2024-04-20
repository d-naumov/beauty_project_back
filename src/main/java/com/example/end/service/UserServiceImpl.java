package com.example.end.service;

import com.example.end.dto.*;
import com.example.end.exceptions.CategoryNotFoundException;
import com.example.end.exceptions.RestException;
import com.example.end.exceptions.UserNotFoundException;
import com.example.end.mail.ProjectMailSender;
import com.example.end.mapping.CategoryMapper;
import com.example.end.mapping.UserMapper;
import com.example.end.models.Category;
import com.example.end.models.Procedure;
import com.example.end.models.User;
import com.example.end.repository.CategoryRepository;
import com.example.end.repository.UserRepository;
import com.example.end.security.sec_servivce.TokenService;
import com.example.end.service.interfaces.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final ProjectMailSender mailSender;
    private final TokenService tokenService;
    @Value("${spring.mail.username}")
    private String adminEmail;

    @Override
    @Transactional
    public UserDto register(NewUserDto newUserDto) {
        validateEmail(newUserDto.getEmail());
        User user = createUser(newUserDto);
        if (user.getRole() == User.Role.MASTER) {
            sendConfirmationEmails(user);
            user.setActive(false);
        } else {
            user.setActive(true);
            sendRegistrationEmail(user);
        }
        userRepository.save(user);


        String accessToken = tokenService.generateAccessToken(user);
        String refreshToken = tokenService.generateRefreshToken(user);


        UserDto userDto = userMapper.toDto(user);
        userDto.setAccessToken(accessToken);
        userDto.setRefreshToken(refreshToken);

        return userDto;
    }

    @Override
    public UserDto authenticate(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RestException(HttpStatus.UNAUTHORIZED, "Неверный email или пароль"));

        if (!passwordEncoder.matches(password, user.getHashPassword())) {
            throw new RestException(HttpStatus.UNAUTHORIZED, "Неверный email или пароль");
        }

        return userMapper.toDto(user);
    }

    @Override
    public UserDetailsDto getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found for id: " + id));
        return userMapper.userDetailsToDto(user);
    }

    @Override
    public void sendConfirmationEmails(User masterUser) {
        String subject = "Ожидание подтверждения регистрации мастера";
        String messageToMaster = "Ваша регистрация в качестве мастера зарегистрирована и ожидает подтверждения администратора. " +
                "Мы свяжемся с вами, как только ваш аккаунт будет подтвержден. Спасибо за регистрацию!";
        mailSender.sendEmail(masterUser.getEmail(), subject, messageToMaster);
        String messageToAdmin = masterUser.getFirstName() + " " + masterUser.getLastName();
        mailSender.sendMasterConfirmationRequest(adminEmail, messageToAdmin);
    }
      @Override
      public void validateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new RestException(HttpStatus.CONFLICT, "User with email <" + email + "> already exists");
        }
    }
  @Override
  public User createUser(NewUserDto newUserDto) {
        return User.builder()
                .firstName(newUserDto.getFirstName())
                .lastName(newUserDto.getLastName())
                .email(newUserDto.getEmail())
                .role(newUserDto.getRole() != null ? newUserDto.getRole() : User.Role.CLIENT)
                .hashPassword(passwordEncoder.encode(newUserDto.getHashPassword()))
                .isActive(newUserDto.getRole() != User.Role.MASTER)
                .build();
    }


    @Override
    @Transactional
    public UserDetailsDto updateUserDetails(Long userId, NewUserDetailsDto userDetailsDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found for id: " + userId));

        user.setDescription(userDetailsDto.getDescription());
        user.setPhoneNumber(userDetailsDto.getPhoneNumber());
        user.setAddress(userDetailsDto.getAddress());

        Set<Category> categories = new HashSet<>(categoryRepository.findAllById(userDetailsDto.getCategoryIds()));
        user.setCategories(categories);

        Set<Procedure> procedures = categories.stream()
                .flatMap(category -> category.getProcedures().stream())
                .collect(Collectors.toSet());
        user.setProcedures(procedures);

        User updatedUser = userRepository.save(user);

        UserDetailsDto responseDto = userMapper.userDetailsToDto(updatedUser);
        responseDto.setCategoryIds(updatedUser.getCategories().stream().map(Category::getId).collect(Collectors.toList()));
        responseDto.setProcedureIds(updatedUser.getProcedures().stream().map(Procedure::getId).collect(Collectors.toList()));

        return responseDto;
    }


    private void sendRegistrationEmail(User user) {
        String subject = "Регистрация на сайте";
        String message = "Поздравляем с успешной регистрацией на нашем сайте!";
        mailSender.sendEmail(user.getEmail(), subject, message);

    }

    @Override
    public UserDto getMasterById(Long id) {
        User master =  userRepository.findByIdAndRole(id, User.Role.MASTER)
                .orElseThrow(() -> new UserNotFoundException("Master not found for id: " + id));
        return userMapper.toDto(master);
    }
    @Override
    public UserDto getClientById(Long id) {
        User client = userRepository.findByIdAndRole(id, User.Role.CLIENT)
                .orElseThrow(() -> new UserNotFoundException("Client not found for id: " + id));
        return userMapper.toDto(client);
    }

    @Override
    @Transactional
    public void confirmMasterByEmail(String email) {
        User masterUser = findMasterUserByEmail(email);
        activateMasterUser(masterUser);
        sendRegistrationEmail(masterUser);
    }
    @Override
    public User findMasterUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        User masterUser = optionalUser.orElseThrow(() -> new UserNotFoundException("Master user not found or already confirmed for email: " + email));
        if (masterUser.getRole() != User.Role.MASTER || masterUser.isActive()) {
            throw new UserNotFoundException("Master user not found or already confirmed for email: " + email);
        }
        return masterUser;
    }
    @Override
    public void activateMasterUser(User masterUser) {
        masterUser.setActive(true);
        userRepository.save(masterUser);
    }

    @Override
    public List<UserDetailsDto> getAllMasters() {
        List<User> masters = userRepository.findAllByRole(User.Role.MASTER);
        return masters.stream()
                .map(userMapper::userDetailsToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDetailsDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::userDetailsToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> loadUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found for id: " + id));
        userRepository.delete(user);
    }
}

