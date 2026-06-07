package com.WAP.auth_Service.exception;

import com.WAP.auth_Service.dto.errorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class globalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<errorResponse> handleEmailAlreadyExistsException(EmailAlreadyExistsException mes){

        errorResponse error=new errorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                mes.getMessage()
        );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<errorResponse> handleInvalidPasswordException(InvalidPasswordException mes){

        errorResponse error=new errorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                mes.getMessage()
        );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<errorResponse> handleUserNotFoundException(UserNotFoundException mes){

        errorResponse error=new errorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                mes.getMessage()
        );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }




}
