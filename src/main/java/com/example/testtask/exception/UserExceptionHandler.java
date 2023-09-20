package com.example.testtask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(value = {UserNotFoundException.class} )
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException userNotFoundException) {
        var userException = new UserException(
                userNotFoundException.getMessage(),
                userNotFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(userException,userException.getHttpStatus());
    }
    @ExceptionHandler(value = {UserExistingEmailException.class} )
    public ResponseEntity<?> UserExistingEmailException(UserExistingEmailException userExistingEmailException) {
        var userException = new UserException(
                userExistingEmailException.getMessage(),
                userExistingEmailException.getCause(),
                HttpStatus.CONFLICT
        );
        return new ResponseEntity<>(userException, userException.getHttpStatus());
    }
}
