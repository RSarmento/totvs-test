package com.totvs.UserRegister.exception;

public class UserRegisterException extends RuntimeException{

    public UserRegisterException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserRegisterException(String message) {
        super(message);
    }
}
