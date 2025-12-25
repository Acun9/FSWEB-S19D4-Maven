package com.workintech.s19d1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleApiException(ApiException apiException) {
        HttpStatus status = apiException.getHttpStatus();
        ExceptionResponse responseBody = new ExceptionResponse(apiException.getMessage(), status.value(), LocalDateTime.now());
        return new ResponseEntity<>(responseBody, status);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(Exception exception) {
        ExceptionResponse responseBody = new ExceptionResponse(exception.getMessage(), 500, LocalDateTime.now());
        return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

