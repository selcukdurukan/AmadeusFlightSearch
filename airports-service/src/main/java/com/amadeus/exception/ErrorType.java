package com.amadeus.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {

    INTERNAL_ERROR(5100, "An unexpected error on the server", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(4111, "Parameter error", HttpStatus.BAD_REQUEST),
    CREATE_AIRPORT_ERROR(1122, "This airport have been already created", HttpStatus.BAD_REQUEST),
    AIRPORT_NOT_FOUND_ERROR(1133, "Airport could not find with giving id", HttpStatus.NOT_FOUND),
    AIRPORT_NOT_FOUND_NAME_ERROR(1144, "Airport could not find with giving name", HttpStatus.NOT_FOUND),
    INVALID_TOKEN(3155,"Token is invalid", HttpStatus.BAD_REQUEST);

    private int code;
    private String message;
    HttpStatus httpStatus;
}
