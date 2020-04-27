package com.ykb.spring.rest.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = MyCustomValidation.class)
public @interface MyStrValid {

    int length();

    String message() default "String validasyonu patladı";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
