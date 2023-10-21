package com.blackbucks.ProductService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvices {
    @ExceptionHandler(ProductNotFoundException.class)

    public ResponseEntity<String> handleException(ProductNotFoundException productNotFoundException) {

        return new ResponseEntity(
                productNotFoundException.getMessage(),
                HttpStatus.NOT_FOUND
        );
    }
}
