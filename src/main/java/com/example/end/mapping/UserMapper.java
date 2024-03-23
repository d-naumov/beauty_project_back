package com.example.end.mapping;

import com.example.end.dto.UserDto;
import com.example.end.models.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public static UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(User.Role.valueOf(user.getRole().toString()))
                .build();
    }

}
