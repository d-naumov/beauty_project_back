package com.example.end.mapping;

import com.example.end.dto.UserDto;
import com.example.end.models.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public UserDto toNameDto(User user) {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    public User toEntity(UserDto user) {
        return User.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}


//    public UserDto toDto(User user) {
//        return UserDto.builder()
//                .id(user.getId())
//                .firstName(user.getFirstName())
//                .lastName(user.getLastName())
//                .email(user.getEmail())
//                .role(User.Role.valueOf(user.getRole().toString()))
//                .build();
//    }
//
//
//    public User toEntity(UserDto userDto) {
//        return User.builder()
//                .id(userDto.getId())
//                .firstName(userDto.getFirstName())
//                .lastName(userDto.getLastName())
//                .email(userDto.getEmail())
//                .role(User.Role.valueOf(userDto.getRole().toString()))
//                .build();
//    }
//}