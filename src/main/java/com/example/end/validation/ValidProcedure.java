package com.example.end.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
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



