package com.test.validateprices.microservice.bean;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
public class ValidationRateResponse {

    private Long productId;
    private Long brandId;
    private int priceList;
    private Timestamp startDate;
    private Timestamp endDate;
    private String finalPrice;

}
