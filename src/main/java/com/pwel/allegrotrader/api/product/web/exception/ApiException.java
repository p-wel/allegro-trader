package com.pwel.allegrotrader.api.product.web.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiException extends RuntimeException {

    String message;
    HttpStatus httpStatus;
    ZonedDateTime zonedDateTime;

    public ApiException(String message, HttpStatus httpStatus, ZonedDateTime zonedDateTime) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.zonedDateTime = zonedDateTime;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
