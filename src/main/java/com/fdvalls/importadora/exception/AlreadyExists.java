package com.fdvalls.importadora.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AlreadyExists extends Exception {
   
    public AlreadyExists(String msg){
        super(msg);
    }
}
