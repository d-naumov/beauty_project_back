package com.example.end.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserValidator.class)
@Target({ElementType.TYPE_USE, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUser {
  String message() default "Invalid user";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}



