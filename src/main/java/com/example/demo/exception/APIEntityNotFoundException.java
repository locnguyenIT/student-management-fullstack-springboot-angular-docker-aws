package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) //set HTTP response code NOT_FOUND = status 404
public class APIEntityNotFoundException extends RuntimeException {
    public APIEntityNotFoundException(String message) {
        super(message);
    }
}
