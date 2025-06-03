package com.stock.stock.monitoring.exception;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class ApplicationException extends RuntimeException {

    public static final ApplicationCode SERVER_ERROR = new ApplicationCodeImpl("500", "Internal Server Error");
    private ApplicationCode applicationCode;
    private String description;
    private List<ApplicationException> nestedExceptions = new ArrayList<>();

    public ApplicationException() {
        this(SERVER_ERROR.message());
    }

    public ApplicationException(String description) {
        super(description);
        applicationCode = SERVER_ERROR;
    }

    public ApplicationException(ApplicationCode applicationCode) {
        super(applicationCode.message());
        this.applicationCode = applicationCode;
    }

    public ApplicationException(ApplicationCode applicationCode, String message) {
        super(applicationCode.code() + "-" + message);
        this.applicationCode = applicationCode;
        this.description = message;
    }
    public ApplicationException(ApplicationCode applicationCode, Throwable cause) {
        super(applicationCode.message(), cause);
        this.applicationCode = applicationCode;
    }

    public ApplicationException(String description, Throwable cause) {
        super(description, cause);
        this.description = description;
        this.applicationCode = SERVER_ERROR;
    }

    public void addNestedException(ApplicationException e) {
        nestedExceptions.add(e);
    }

    public List<ApplicationException> getNestedExceptions() {
        return nestedExceptions;
    }

    public ApplicationCode getApplicationCode() {
        return applicationCode;
    }

    public String getDescription() {
        return description;
    }

    static class ApplicationCodeImpl implements ApplicationCode {

        private String code;
        private String message;
        private HttpStatus http;

        public ApplicationCodeImpl(String code, String message) {
            this.code = code;
            this.message = message;
            this.http = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        public ApplicationCodeImpl(String code, String message, HttpStatus http) {
            this.code = code;
            this.message = message;
            this.http = http;
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
            return http;
        }
    }
}
