package com.demo.jwt.model.rdo;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
public class ExceptionResponse<T> {

    private Integer statusCode;
    private String message;
    private String debugMessage;

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public String getMessage() {
        return message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public ExceptionResponse(String message, HttpStatus status, String debugMessage) {
        this.statusCode = status.value();
        this.message = message;
        this.debugMessage = debugMessage;
    }
}
