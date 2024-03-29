package com.example.end.service;

import com.example.end.dto.NewUserDto;
import com.example.end.dto.UserDto;
import com.example.end.exceptions.RestException;
import com.example.end.mail.ProjectMailSender;
import com.example.end.mapping.UserMapper;
import com.example.end.models.User;
import com.example.end.repository.UserRepository;
import com.example.end.service.interfaces.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final ProjectMailSender mailSender;
    @Value("${spring.mail.username}")
    private String adminEmail;

    @Override
    @Transactional
    public UserDto register(NewUserDto newUserDto) {
        // Проверяем, существует ли пользователь с таким email
        validateEmail(newUserDto.getEmail());
        // Создаем нового пользователя
        User user = createUser(newUserDto);

        // Проверяем, является ли пользователь мастером
        if (user.getRole() == User.Role.MASTER) {
            // Отправляем уведомление администратору о необходимости подтверждения нового мастера
            sendConfirmationEmails(user);
            // Мастер по умолчанию неактивен до подтверждения администратором
            user.setActive(false);
        } else {
            // Клиент сразу активен после регистрации
            user.setActive(true);
            // Отправляем письмо о регистрации только для клиентов
            sendRegistrationEmail(user);
        }
        // Сохраняем пользователя в репозитории
        userRepository.save(user);

        return userMapper.toDto(user);
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

    // Отправка писем о подтверждении
    private void sendConfirmationEmails(User masterUser) {
        // Формирование темы письма
        String subject = "Ожидание подтверждения регистрации мастера";
        // Формирование сообщения для мастера
        String messageToMaster = "Ваша регистрация в качестве мастера зарегистрирована и ожидает подтверждения администратора. " +
                "Мы свяжемся с вами, как только ваш аккаунт будет подтвержден. Спасибо за регистрацию!";
        // Отправка письма мастеру
        mailSender.sendEmail(masterUser.getEmail(), subject, messageToMaster);

        // Формирование сообщения для администратора
        String messageToAdmin = masterUser.getFirstName() + " " + masterUser.getLastName();
        // Отправка запроса на подтверждение администратору
        mailSender.sendMasterConfirmationRequest(adminEmail, messageToAdmin);
    }

    // Проверка существования пользователя по email
    private void validateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new RestException(HttpStatus.CONFLICT, "User with email <" + email + "> already exists");
        }
    }

    // Создание нового пользователя
    private User createUser(NewUserDto newUserDto) {
        return User.builder()
                .firstName(newUserDto.getFirstName())
                .lastName(newUserDto.getLastName())
                .email(newUserDto.getEmail())
                .role(newUserDto.getRole() != null ? newUserDto.getRole() : User.Role.CLIENT)
                .hashPassword(passwordEncoder.encode(newUserDto.getHashPassword()))
                .isActive(newUserDto.getRole() != User.Role.MASTER)
                .build();
    }

    // Отправка письма о регистрации
    private void sendRegistrationEmail(User user) {
        String subject = "Регистрация на сайте";
        String message = "Поздравляем с успешной регистрацией на нашем сайте!";
        mailSender.sendEmail(user.getEmail(), subject, message);

    }

    @Override
    public UserDto getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found for id: " + id));
        return userMapper.toDto(user);
    }

    @Override
    @Transactional
    public void confirmMasterByEmail(String email) {
        // Поиск пользователя-мастера и его активация
        User masterUser = findMasterUserByEmail(email);
        activateMasterUser(masterUser);
        // Отправка писем о подтверждении
        sendRegistrationEmail(masterUser);
    }

    // Поиск пользователя-мастера по email
    private User findMasterUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        User masterUser = optionalUser.orElseThrow(() -> new IllegalArgumentException("Master user not found or already confirmed for email: " + email));
        if (masterUser.getRole() != User.Role.MASTER || masterUser.isActive()) {
            throw new IllegalArgumentException("Master user not found or already confirmed for email: " + email);
        }
        return masterUser;
    }

    // Активация пользователя-мастера
    private void activateMasterUser(User masterUser) {
        masterUser.setActive(true);
        userRepository.save(masterUser);
    }



    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found for id: " + id));
        userRepository.delete(user);
    }
}

