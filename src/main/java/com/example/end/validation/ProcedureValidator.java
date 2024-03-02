package com.example.end.validation;


import com.example.end.dto.ProcedureWithPriceDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ProcedureValidator implements
    ConstraintValidator<ValidProcedure, ProcedureWithPriceDto> {

  @Override
  public void initialize(ValidProcedure constraintAnnotation) {
    // Инициализация, если необходимо
  }

  @Override
  public boolean isValid(ProcedureWithPriceDto procedureDto, ConstraintValidatorContext context) {
    // Реализуйте логику валидации данных процедуры здесь
    // Вернуть true, если данные валидны, и false в противном случае

    return false; // Замените это на вашу реализацию
  }
}
