package com.taller_app.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerExceptions {

    @ExceptionHandler(value = GeneralException.class)
    public ResponseEntity<ErrorResponse> handleGeneralExceptions (GeneralException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ex.getHttpStatus());
        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }
}
