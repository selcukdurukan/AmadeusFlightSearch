package com.amadeus.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {

    INTERNAL_ERROR(5200, "An unexpected error on the server", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(4211, "Parameter error", HttpStatus.BAD_REQUEST),
    CREATE_FLIGHT_ERROR(1222, "Given airports name cannot find. Check names", HttpStatus.BAD_REQUEST),
    FLIGHT_NOT_FOUND_ERROR(1233, "Flight could not find with giving string id", HttpStatus.NOT_FOUND),
    UPDATE_SAME_AIRPORTS_ERROR(1244, "Giving airports' names must not be same", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(3255,"Token is invalid", HttpStatus.BAD_REQUEST);

    private int code;
    private String message;
    HttpStatus httpStatus;
}
