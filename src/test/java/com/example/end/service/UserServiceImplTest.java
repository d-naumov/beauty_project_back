package com.example.end.service;
import com.example.end.dto.NewUserDto;
import com.example.end.dto.UserDto;
import com.example.end.exceptions.RestException;
import com.example.end.mail.ProjectMailSender;
import com.example.end.mapping.UserMapper;
import com.example.end.models.User;
import com.example.end.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ProjectMailSender mailSender;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        // Здесь можно настроить дополнительные настройки перед каждым тестом
    }

    @Test
    public void testRegisterNewUserSuccess() {
        // Arrange
        NewUserDto newUserDto = new NewUserDto("John", "Doe", "john@example.com", "Qwerty123!", User.Role.CLIENT);
        when(userRepository.existsByEmail(newUserDto.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(newUserDto.getHashPassword())).thenReturn("hashedPassword");

        // Создаем ожидаемого пользователя
        User expectedUser = User.builder()
                .firstName(newUserDto.getFirstName())
                .lastName(newUserDto.getLastName())
                .email(newUserDto.getEmail())
                .role(newUserDto.getRole())
                .hashPassword("hashedPassword")
                .isActive(true)
                .build();

        // Создаем ожидаемый UserDto
        UserDto expectedUserDto = new UserDto();

        // Заглушаем метод toDto
        when(userMapper.toDto(any(User.class))).thenReturn(expectedUserDto);

        // Act
        UserDto result = userService.register(newUserDto);

        // Assert
        assertNotNull(result);
        // Добавьте другие проверки, если это необходимо

        // Выводим результат теста в консоль
        System.out.println("Результат теста testRegister_NewUser_Success:");
        System.out.println("Успех: " + result);
    }

    @Test
    public void testRegisterUserWithEmailAlreadyExistsExceptionThrown() {
        // Arrange
        NewUserDto existingUserDto = new NewUserDto("Jane", "Doe", "jane@example.com", "Qwerty123!", User.Role.CLIENT);
        when(userRepository.existsByEmail(existingUserDto.getEmail())).thenReturn(true);

        // Assert
        assertThrows(RestException.class, () -> userService.register(existingUserDto));

        // Выводим результат теста в консоль
        System.out.println("Результат теста testRegister_UserWithEmailAlreadyExists_ExceptionThrown:");
        System.out.println("Исключение успешно вызвано при попытке зарегистрировать пользователя с существующим email.");
    }
}

