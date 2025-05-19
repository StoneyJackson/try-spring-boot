package com.in28minutes.rest.webservices.try_spring_boot.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.in28minutes.rest.webservices.try_spring_boot.user.UserNotFoundException;

@ControllerAdvice
public class CustomResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request) throws Exception {
        ErrorDetails details = new ErrorDetails(LocalDateTime.now(),
            ex.getMessage(),
            request.getDescription(false)
        );
        return new ResponseEntity<ErrorDetails>(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {
        ErrorDetails details = new ErrorDetails(LocalDateTime.now(),
            ex.getMessage(),
            request.getDescription(false)
        );
        return new ResponseEntity<ErrorDetails>(details, HttpStatus.NOT_FOUND);
    }
}
