package com.example.challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<Object> badRequestException(BadRequestException e){
        return new ResponseEntity<>(new ApiException(HttpStatus.BAD_REQUEST, e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> badRequestException(NotFoundException e){
        return new ResponseEntity<>(new ApiException(HttpStatus.NOT_FOUND, e.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ConflictException.class})
    public ResponseEntity<Object> badRequestException(ConflictException e){
        return new ResponseEntity<>(new ApiException(HttpStatus.CONFLICT, e.getMessage()),
                HttpStatus.CONFLICT);
    }

}
