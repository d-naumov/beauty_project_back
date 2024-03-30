package com.example.end.service.interfaces;

import com.example.end.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    UserDto authenticate(String email, String password);
}