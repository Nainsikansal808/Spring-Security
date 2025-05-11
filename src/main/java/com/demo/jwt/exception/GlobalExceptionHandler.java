package com.demo.jwt.exception;

import com.demo.jwt.model.rdo.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse<EntityNotFoundException>> handleEntityNotFound(EntityNotFoundException ex){

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ExceptionResponse<>("Not Found Exception", HttpStatus.NOT_FOUND, ex.getLocalizedMessage()));
   }

   @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ExceptionResponse<AuthenticationException>> handleAuthenticationException(AuthenticationException ex){

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ExceptionResponse<>("UnAuthorized",HttpStatus.UNAUTHORIZED, ex.getLocalizedMessage()));
   }



}
