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
    // Реализуйте логику валидации данных пользователя здесь
    // Вернуть true, если данные валидны, и false в противном случае

    return false; // Замените это на вашу реализацию
  }
}
