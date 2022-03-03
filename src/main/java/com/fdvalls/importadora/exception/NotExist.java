package com.fdvalls.importadora.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class NotExist extends Exception {
    
    public NotExist (String msg){
        super(msg);
    }
}
