package com.mft.redisarchitecture.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorResponse> handleAppException(AppException ex) {
        ErrorResponse response = new ErrorResponse(
                ex.errorCode,
                ex.message
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }



    @Data
    @AllArgsConstructor
    static class ErrorResponse {
        private int errorCode;
        private String message;
    }

}
