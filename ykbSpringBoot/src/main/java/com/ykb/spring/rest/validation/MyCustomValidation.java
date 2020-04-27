package com.ykb.spring.rest.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyCustomValidation implements ConstraintValidator<MyStrValid, String> {

    private MyStrValid constraintAnnotation;

    @Override
    public void initialize(final MyStrValid constraintAnnotationParam) {
        this.constraintAnnotation = constraintAnnotationParam;

    }

    @Override
    public boolean isValid(final String valueParam,
                           final ConstraintValidatorContext contextParam) {
        int len = 0;
        if (this.constraintAnnotation != null) {
            len = this.constraintAnnotation.length();
        }
        return valueParam.length() > len;
    }

}
