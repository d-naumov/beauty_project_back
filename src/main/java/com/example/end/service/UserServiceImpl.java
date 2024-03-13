package com.example.end.service;

import com.example.end.dto.UserDto;
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

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User registerNewUser(UserDto userRegistration) {
        User existingUser = userRepository.findByUsername(userRegistration.getUsername());
        if (existingUser != null) {
            throw new RuntimeException(
                    "User with username " + userRegistration.getUsername() + " already exists!");
        }
        User newUser = new User();
        newUser.setUsername(userRegistration.getUsername());
        newUser.setFirstName(userRegistration.getFirstName());
        newUser.setLastName(userRegistration.getLastName());
        newUser.setEmail(userRegistration.getEmail());
        newUser.setHashPassword(passwordEncoder.encode(userRegistration.getPassword()));
        newUser.setActive(true);

        Role userRole = roleRepository.findByName("ROLE_USER");
        newUser.setRoles(Collections.singleton(userRole));


        return userRepository.save(newUser);
    }
//    @Transactional
//    public User registerNewUser(UserDto userRegistration) {
//        User existingUser = userRepository.findByUsername(userRegistration.getUsername());
//        if (existingUser != null) {
//            throw new RuntimeException(
//                    "User with username " + userRegistration.getUsername() + " already exists!");
//        }
//
//        User newUser = new User();
//        newUser.setUsername(userRegistration.getUsername());
//        newUser.setFirstName(userRegistration.getFirstName());
//        newUser.setLastName(userRegistration.getLastName());
//        newUser.setEmail(userRegistration.getEmail());
//        newUser.setHashPassword(passwordEncoder.encode(userRegistration.getPassword()));
//        newUser.setActive(true);
//
//        // Назначаем роль пользователю
//        Role userRole = roleRepository.findByName(userRegistration.getRoleName());
//        if (userRole == null) {
//            throw new RuntimeException("Role not found: " + userRegistration.getRoleName());
//        }
//        newUser.setRoles(Collections.singleton(userRole));
//
//        return userRepository.save(newUser);
//    }


    @Transactional
    public Set<User> registerNewUserWithRoles(UserDto userDto, Set<Role> roles) {
        User user = registerNewUser(userDto);
        user.setRoles(roles);
        userRepository.save(user);
        return Set.of(user);
    }


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
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(int id) {
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
