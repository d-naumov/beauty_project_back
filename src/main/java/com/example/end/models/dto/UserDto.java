package com.example.end.models.dto;


import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

  private Long id;

  @NotBlank(message = "Имя пользователя не может быть пустым")
  private String username;

  @Email(message = "Некорректный формат электронной почты")
  @NotBlank(message = "Email is required")
  private String email;

  @NotBlank(message = "Пароль не может быть пустым")
  @Size(min = 6, message = "Пароль должен содержать не менее 6 символов")
  private String password;

  private String firstName;

  private String lastName;



}

