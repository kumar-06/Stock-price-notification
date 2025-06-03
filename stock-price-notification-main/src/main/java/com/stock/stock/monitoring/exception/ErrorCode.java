package com.stock.stock.monitoring.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode implements ApplicationCode {

    BAD_REQUEST("INV400", "Bad request", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("INV400", "INTERNAL SERVER ERROR", HttpStatus.INTERNAL_SERVER_ERROR);;


    private String code;
    private String message;
    private HttpStatus httpCode;

    ErrorCode(String code, String message, HttpStatus httpCode) {
        this.code = code;
        this.message = message;
        this.httpCode = httpCode;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public HttpStatus httpCode() {
        return httpCode;
    }

    public boolean equals(ApplicationCode applicationCode) {
        return this.code.equalsIgnoreCase(applicationCode.code());
    }
}
