package com.test.validateprices.microservice.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationRatesException extends RuntimeException{

    private RateError rateError;

    public ValidationRatesException(RateError rateError){
        super();
        this.rateError = rateError;
    }
}
