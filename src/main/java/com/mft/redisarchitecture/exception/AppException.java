package com.mft.redisarchitecture.exception;

public class AppException extends RuntimeException {
    String message;
    int errorCode;

    public AppException(String message, int errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }


}
