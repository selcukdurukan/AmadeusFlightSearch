package com.amadeus.exception;

import lombok.Getter;

@Getter
public class FlightAppException extends  RuntimeException{

    private final ErrorType errorType;

    public FlightAppException(ErrorType errorType) {
        this.errorType = errorType;
    }

    public FlightAppException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }
}
