package com.amadeus.exception;

import lombok.Getter;

@Getter
public class AuthAppException extends  RuntimeException{

    private final ErrorType errorType;

    public AuthAppException(ErrorType errorType) {
        this.errorType = errorType;
    }

    public AuthAppException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }
}
