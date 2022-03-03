package com.fdvalls.importadora.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DealerNotFoundException extends Exception {

    public DealerNotFoundException(String message) {
        super(message);
    }
    
}
