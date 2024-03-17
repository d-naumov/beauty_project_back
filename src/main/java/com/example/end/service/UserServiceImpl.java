package com.example.end.service;

import com.example.end.dto.UserDto;
import com.example.end.mail.ProjectMailSender;
import com.example.end.models.Category;
import com.example.end.models.Procedure;
import com.example.end.models.Role;
import com.example.end.models.User;
import com.example.end.repository.RoleRepository;
import com.example.end.repository.UserRepository;
import com.example.end.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProjectMailSender mailSender; //  сервис отправки электронной почты
    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, ProjectMailSender mailSender) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
    }
    @Transactional
    public User registerNewUser(UserDto userDto) {
        User newUser = new User();
        newUser.setFirstName(userDto.getFirstName());
        newUser.setLastName(userDto.getLastName());
        newUser.setEmail(userDto.getEmail());
        newUser.setHashPassword(passwordEncoder.encode(userDto.getPassword()));

        // Проверяем выбранную роль
        String roleName = userDto.getRoleName();
        if (roleName != null && roleName.equals("MASTER")) {
            // Регистрируем мастера
            Role masterRole = roleRepository.findByName("MASTER");
            if (masterRole == null) {
                throw new RuntimeException("Master role not found!");
            }
            newUser.setRoles(Collections.singleton(masterRole));
            newUser.setActive(false); // Помечаем мастера как неактивного

            // Отправляем уведомление администратору о новом мастере
            String adminEmail = "admin@example.com"; // Замените на реальный адрес администратора
            String subject = "Новый мастер ожидает подтверждения";
            String message = "Пользователь " + newUser.getLastName() + " ожидает подтверждения вашим администратором.";
            mailSender.sendEmail(adminEmail, subject, message);
        } else {
            // Регистрируем пользователя с ролью ROLE_USER
            Role userRole = roleRepository.findByName("CLIENT");
            if (userRole == null) {
                throw new RuntimeException("User role not found!");
            }
            newUser.setRoles(Collections.singleton(userRole));
            newUser.setActive(true); // Пользователь по умолчанию активен

            // Отправляем письмо пользователю о подтверждении регистрации
            String subject = "Регистрация на сайте";
            String message = "Поздравляем с успешной регистрацией на нашем сайте!";
            mailSender.sendEmail(newUser.getEmail(), subject, message);
        }

        return userRepository.save(newUser);
    }

    // Метод для подтверждения нового мастера администратором
    @Transactional
    public void confirmMaster(String masterUsername) {
        // Помечаем пользователя с указанным именем как активного мастера
        User masterUser = userRepository.findByUsername(masterUsername);
        if (masterUser != null) {
            masterUser.setActive(true);
            userRepository.save(masterUser);

            // Отправляем подтверждение мастеру
            String subject = "Регистрация мастера подтверждена";
            String message = "Ваша регистрация в качестве мастера подтверждена. Теперь вы можете начать использовать наш сервис.";
            mailSender.sendEmail(masterUser.getEmail(), subject, message);

            // Отправляем уведомление администратору о подтверждении мастера
            String adminEmail = "admin@example.com"; // Замените на реальный адрес администратора
            String adminSubject = "Регистрация мастера подтверждена";
            String adminMessage = "Регистрация мастера " + masterUsername + " успешно подтверждена.";
            mailSender.sendEmail(adminEmail, adminSubject, adminMessage);

            // Отправляем уведомление мастеру о подтверждении его регистрации
            mailSender.sendMasterConfirmationRequest(masterUsername, masterUser.getLastName());
        } else {
            throw new IllegalArgumentException("Master user not found: " + masterUsername);
        }
    }


//    @Transactional
//    public User registerNewUser(UserDto userDto) {
//        User newUser = new User();
//        newUser.setUsername(userDto.getUsername());
//        newUser.setFirstName(userDto.getFirstName());
//        newUser.setLastName(userDto.getLastName());
//        newUser.setEmail(userDto.getEmail());
//        newUser.setHashPassword(passwordEncoder.encode(userDto.getPassword()));
//
//        // Проверяем выбранную роль
//        String roleName = userDto.getRoleName();
//        if (roleName != null && roleName.equals("ROLE_MASTER")) {
//            // Регистрируем мастера
//            Role masterRole = roleRepository.findByName("ROLE_MASTER");
//            if (masterRole == null) {
//                throw new RuntimeException("Master role not found!");
//            }
//            newUser.setRoles(Collections.singleton(masterRole));
//            newUser.setActive(false); // Помечаем мастера как неактивного
//
//            // Отправляем уведомление администратору о новом мастере
//            String adminEmail = "admin@example.com"; // Замените на реальный адрес администратора
//            String subject = "Новый мастер ожидает подтверждения";
//            String message = "Пользователь " + newUser.getUsername() + " ожидает подтверждения вашим администратором.";
//            mailSender.sendEmail(adminEmail, subject, message);
//        } else {
//            // Регистрируем пользователя с ролью ROLE_USER
//            Role userRole = roleRepository.findByName("ROLE_USER");
//            if (userRole == null) {
//                throw new RuntimeException("User role not found!");
//            }
//            newUser.setRoles(Collections.singleton(userRole));
//            newUser.setActive(true); // Пользователь по умолчанию активен
//
//            // Отправляем письмо пользователю о подтверждении регистрации
//            String subject = "Регистрация на сайте";
//            String message = "Поздравляем с успешной регистрацией на нашем сайте!";
//            mailSender.sendEmail(newUser.getEmail(), subject, message);
//        }
//
//        return userRepository.save(newUser);
//    }



    @Transactional
    public void updateMasterData(User master, Set<Category> categories, Set<Procedure> procedures) {
        master.setCategories(categories);
        master.setProcedures(procedures);
        userRepository.save(master);
    }



    @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username) {
        return userRepository.findByEmail(username);
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
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }


    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}

