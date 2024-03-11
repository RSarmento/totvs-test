package com.totvs.UserRegister.exception;

/**
 * The type User register exception.
 */
public class UserRegisterException extends RuntimeException{

    /**
     * Instantiates a new User register exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public UserRegisterException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new User register exception.
     *
     * @param message the message
     */
    public UserRegisterException(String message) {
        super(message);
    }
}
