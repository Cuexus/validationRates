package com.test.validateprices.microservice.service;

import com.test.validateprices.microservice.bean.ValidationRateResponse;
import com.test.validateprices.microservice.exception.ValidationRatesException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
@Service
public interface PricesService {

    ValidationRateResponse getRatesByApplicationDate(Long productId, Long brandId, Timestamp applicationDate) throws ValidationRatesException;

}
