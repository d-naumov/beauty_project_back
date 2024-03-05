package com.example.end.validation;


import com.example.end.dto.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class UserValidator implements ConstraintValidator<ValidUser, UserDto> {

  @Override
  public void initialize(ValidUser constraintAnnotation) {
    // Инициализация, если необходимо

  }

  @Override
  public boolean isValid(UserDto userDto, ConstraintValidatorContext context) {
    if (userDto == null) {
      return false;
    }

    // Проверка, что пароль содержит хотя бы одну цифру, одну букву верхнего регистра и одну букву нижнего регистра
    if (!passwordIsValid(userDto.getPassword())) {
      return false;
    }

    // Проверка, что поле email не содержит слово "admin"
    if (userDto.getEmail() != null && userDto.getEmail().toLowerCase().contains("admin")) {
      return false;
    }

    // Дополнительные проверки...

    return true; // Замените это на вашу реализацию
  }

  private boolean passwordIsValid(String password) {
    // Проверка, что пароль содержит хотя бы одну цифру, одну букву верхнего регистра и одну букву нижнего регистра
    return password.matches(".*\\d.*") && password.matches(".*[A-Z].*") && password.matches(".*[a-z].*");
  }
}
