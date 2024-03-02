package com.example.end.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ProcedureValidator.class})
@Documented
public @interface ValidProcedure {

  String message() default "Неверные данные процедуры";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}



