package com.stock.stock.monitoring.exception;

import org.springframework.http.HttpStatus;

public interface ApplicationCode {

    String code();
    String message();
    HttpStatus httpCode();
}
