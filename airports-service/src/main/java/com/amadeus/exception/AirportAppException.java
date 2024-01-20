package com.amadeus.exception;

import lombok.Getter;

@Getter
public class AirportAppException extends  RuntimeException{

    private final ErrorType errorType;

    public AirportAppException(ErrorType errorType) {
        this.errorType = errorType;
    }

    public AirportAppException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }
}
