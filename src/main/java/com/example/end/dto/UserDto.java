package com.example.end.dto;


import com.example.end.validation.ValidUser;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ValidUser
    @Data
    public class UserDto {

        @Email(message = "Некорректный формат электронной почты")
        @NotBlank(message = "Email не может быть пустым")
        private String email;

        @NotBlank(message = "Пароль не может быть пустым")
        @Size(min = 6, message = "Пароль должен содержать не менее 6 символов")
        private String password;

        @NotBlank(message = "Имя не может быть пустым")
        @Pattern(regexp = "[A-Z][a-z]{3,}", message = "Имя должно начинаться с большой буквы и содержать не менее 3 символов")
        private String firstName;

        @NotBlank(message = "Фамилия не может быть пустой")
        @Pattern(regexp = "[A-Z][a-z]{3,}", message = "Фамилия должна начинаться с большой буквы и содержать не менее 3 символов")
        private String lastName;

        public UserDto() {
        }

        public UserDto(String firstName, String lastName,String email, String password ) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.password = password;
        }
    }
