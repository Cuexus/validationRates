package com.test.validateprices.microservice.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RateError {

    private int code;
    private String message;
    private String cause;

    public RateError(int code, String message, String cause) {
        this.code = code;
        this.message = message;
        this.cause = cause;
    }

    @Override
    public String toString() {
        return "RateError{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", cause='" + cause + '\'' +
                '}';
    }
}
