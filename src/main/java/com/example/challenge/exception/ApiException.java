package com.example.challenge.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import java.util.Date;

@Data
@AllArgsConstructor
public class ApiException {

    private final Date timestamp = new Date();
    private final HttpStatus httpStatus;
    private final String message;

}
