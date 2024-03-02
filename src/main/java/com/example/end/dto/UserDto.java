package com.example.end.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


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



  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}


}

