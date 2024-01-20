package com.amadeus.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {

    INTERNAL_ERROR(5000, "An unexcepted error on the server", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(4011, "Parameter error", HttpStatus.BAD_REQUEST),
    REGISTER_PASSWORD_ERROR(3022, "Retry-password and password must be equal", HttpStatus.BAD_REQUEST),
    REGISTERED_USER_ERROR(3033, "E-mail has already registered", HttpStatus.BAD_REQUEST),
    LOGIN_ERROR(3044, "Email or password is incorrect", HttpStatus.BAD_REQUEST),
    JWT_TOKEN_CREATE_ERROR(3055, "Token error", HttpStatus.BAD_REQUEST);

    private int code;
    private String message;
    HttpStatus httpStatus;
}
