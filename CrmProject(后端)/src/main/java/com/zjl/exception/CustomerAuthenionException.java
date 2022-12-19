package com.zjl.exception;

import org.springframework.security.core.AuthenticationException;

public class CustomerAuthenionException extends  AuthenticationException{
    public CustomerAuthenionException(String msg) {
        super(msg);
    }
}
