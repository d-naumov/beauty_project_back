package com.example.end.service.interfaces;


import com.example.end.dto.NewUserDto;
import com.example.end.dto.NewUserDetailsDto;
import com.example.end.dto.UserDetailsDto;
import com.example.end.dto.UserDto;
import com.example.end.models.User;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface UserService {

    @Transactional
    UserDto register(NewUserDto newUserDto);

    UserDto authenticate(String email, String password);

    UserDto getById(Long id);

    void sendConfirmationEmails(User masterUser);

    void validateEmail(String email);

    User createUser(NewUserDto newUserDto);

    @jakarta.transaction.Transactional
    UserDetailsDto updateUserDetails(Long userId, NewUserDetailsDto userDetailsDto);

    UserDto getMasterById(Long id);

    UserDto getClientById(Long id);

    @jakarta.transaction.Transactional
    void confirmMasterByEmail(String email);

    Optional<User> findByEmail(String email);

    Optional<User> loadUserByEmail(String email);

    List<UserDto> getAllUsers();

    void deleteById(Long id);

    User findMasterUserByEmail(String email);

    void activateMasterUser(User masterUser);

    List<UserDto> getAllMasters();


}


