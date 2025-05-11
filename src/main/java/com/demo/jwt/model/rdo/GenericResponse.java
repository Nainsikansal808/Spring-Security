package com.demo.jwt.model.rdo;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GenericResponse<T> {

    private int statusCode;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    private String message;
    private T data;

    public GenericResponse(int status, String message, T data) {
        this.statusCode = status;
        this.message = message;
        this.data = data;
    }

}
