package com.example.end.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UserValidator.class})
@Documented
public @interface ValidUser {
  String message() default "Неверные данные пользователя";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}



