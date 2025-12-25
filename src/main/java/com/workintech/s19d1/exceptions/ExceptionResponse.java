package com.workintech.s19d1.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ExceptionResponse {

    private String message;
    private int status;
    private LocalDateTime dateTime;
}

