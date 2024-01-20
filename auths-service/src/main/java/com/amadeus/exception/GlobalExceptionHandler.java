package com.amadeus.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public final ResponseEntity<ErrorMessage> handleAllExceptions(Exception exception) {
        ErrorType errorType = ErrorType.INTERNAL_ERROR;
        List<String> fields = new ArrayList<>();
        fields.add(exception.getMessage());
        ErrorMessage errorMessage = createError(errorType, exception);
        errorMessage.setFields(fields);
        return new ResponseEntity<>(errorMessage, errorType.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public final ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        ErrorType errorType = ErrorType.BAD_REQUEST;
        List<String> fields = new ArrayList<>();
        exception
                .getBindingResult()
                .getFieldErrors()
                .forEach(e -> fields.add(e.getField() + ": " + e.getDefaultMessage()));

        ErrorMessage errorMessage = createError(errorType, exception);

        errorMessage.setFields(fields);

        return new ResponseEntity<>(errorMessage, errorType.getHttpStatus());
    }


    @ExceptionHandler(AuthAppException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleManagerException(AuthAppException ex) {
        ErrorType errorType = ex.getErrorType();
        HttpStatus httpStatus = errorType.getHttpStatus();

        return new ResponseEntity<>(createError(errorType, ex), httpStatus);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public final ResponseEntity<ErrorMessage> handleMessageNotReadableException(
            HttpMessageNotReadableException exception) {
        ErrorType errorType = ErrorType.BAD_REQUEST;
        return new ResponseEntity<>(createError(errorType, exception), errorType.getHttpStatus());
    }

    @ExceptionHandler(InvalidFormatException.class)
    @ResponseBody
    public final ResponseEntity<ErrorMessage> handleInvalidFormatException(
            InvalidFormatException exception) {
        ErrorType errorType = ErrorType.BAD_REQUEST;
        return new ResponseEntity<>(createError(errorType, exception), errorType.getHttpStatus());
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public final ResponseEntity<ErrorMessage> handleMethodArgumentMisMatchException(
            MethodArgumentTypeMismatchException exception) {

        ErrorType errorType = ErrorType.BAD_REQUEST;
        return new ResponseEntity<>(createError(errorType, exception), errorType.getHttpStatus());
    }

    @ExceptionHandler(MissingPathVariableException.class)
    @ResponseBody
    public final ResponseEntity<ErrorMessage> handleMethodArgumentMisMatchException(
            MissingPathVariableException exception) {

        ErrorType errorType = ErrorType.BAD_REQUEST;
        return new ResponseEntity<>(createError(errorType, exception), errorType.getHttpStatus());
    }

    /**
     * Eğer entityde username gibi bir değer unique olması gerekiyorsa, column(unique = true) annotation'ı ile de
     * yapabiliriz, yani IAuthRepository'e findOptionalByUsername methodunu kullanmadan.
     * Eğer unique olması gereken değer unique değilse DataIntegrityBiolationException error fırlatır.
     * Bunu save methodu içinde try-catch ile deneyip catch kısmına throw new AuthServiceException(ErrorType.REGISTER_EMAIL_ERROR);
     * ile handle edebiliriz.
     * @param exception
     * @return
     */
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    @ResponseBody
//    public final ResponseEntity<ErrorMessage> handlePSQLException(DataIntegrityViolationException exception) {
//        ErrorType errorType = ErrorType.REGISTER_USERNAME_ERROR;
//        return new ResponseEntity<>(createError(errorType, exception), errorType.getHttpStatus());
//    }



    private ErrorMessage createError(ErrorType errorType, Exception exception) {
        System.out.println("Hata oluştu: " + exception.getMessage());
        return ErrorMessage.builder()
                .code(errorType.getCode())
                .message(errorType.getMessage())
                .build();
    }


}
