package com.example.end.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter @Setter
public class UserDto {

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


  public UserDto() {
  }

  public UserDto(String username, String email, String password, String firstName, String lastName) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
  }

}

