package com.ykb.spring.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObj> handleException(final Exception exp) {
        ErrorObj errorObjLoc = new ErrorObj();
        errorObjLoc.setErrorDesc(exp.getMessage());
        errorObjLoc.setErrorCause(1157);
        if (exp instanceof IllegalArgumentException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body(errorObjLoc);
        } else if (exp instanceof IllegalStateException) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                                 .body(errorObjLoc);

        } else if (exp instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException manve = (MethodArgumentNotValidException) exp;
            List<ObjectError> allErrorsLoc = manve.getBindingResult()
                                                  .getAllErrors();
            for (ObjectError objectErrorLoc : allErrorsLoc) {
                errorObjLoc.addError(new ErrorObj(objectErrorLoc.getDefaultMessage(),
                                                  15087));
            }
            errorObjLoc.setErrorDesc("Validation Error");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body(errorObjLoc);

        } else {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                                 .body(errorObjLoc);
        }

    }

}
