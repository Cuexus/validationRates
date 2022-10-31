package com.test.validateprices.microservice.controller;

import com.test.validateprices.microservice.bean.ValidationRateResponse;
import com.test.validateprices.microservice.exception.RateError;
import com.test.validateprices.microservice.exception.ValidationRatesException;
import com.test.validateprices.microservice.model.Prices;
import com.test.validateprices.microservice.service.impl.PricesServiceImpl;
import org.jeasy.random.EasyRandom;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.sql.Timestamp;
import java.text.ParseException;

@ExtendWith(MockitoExtension.class)
public class PricesControllerTest {

    @InjectMocks
    PricesController pricesControllerTest;

    @Mock
    PricesServiceImpl pricesService;

    EasyRandom er = new EasyRandom();

    @Before
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findRateByApplicationDate_OKResponse() throws ParseException {
        //GIVEN
        Long productId = 5213123L;
        Long brandId = 12L;
        Timestamp time = new Timestamp(System.currentTimeMillis());
        Prices finalPrice = er.nextObject(Prices.class);
        ValidationRateResponse response = ValidationRateResponse.builder()
                .productId(finalPrice.getProductId())
                .brandId(finalPrice.getBrandId())
                .priceList(finalPrice.getPriceList())
                .startDate(finalPrice.getStartDate())
                .endDate(finalPrice.getEndDate())
                .price(finalPrice.getPrice())
                .currency(finalPrice.getCurrency())
                .finalPrice(finalPrice.getPrice()+finalPrice.getCurrency()).build();
        //WHEN
        Mockito.when(pricesService.getRatesByApplicationDate(productId, brandId, time)).thenReturn(response);

        //THEN

        ResponseEntity resp = pricesControllerTest.getRatesByApplicationDate(productId, brandId, time);
        Assert.assertEquals(HttpStatus.OK, resp.getStatusCode());

    }

    @Test
    void findRateByApplicationDate_KO() throws ParseException {
        //GIVEN
        Long productId = 5213123L;
        Long brandId = 12L;
        Timestamp time = new Timestamp(System.currentTimeMillis());
        Prices finalPrice = er.nextObject(Prices.class);
        ValidationRateResponse response = ValidationRateResponse.builder()
                .productId(finalPrice.getProductId())
                .brandId(finalPrice.getBrandId())
                .priceList(finalPrice.getPriceList())
                .startDate(finalPrice.getStartDate())
                .endDate(finalPrice.getEndDate())
                .price(finalPrice.getPrice())
                .currency(finalPrice.getCurrency())
                .finalPrice(finalPrice.getPrice()+finalPrice.getCurrency()).build();
        //WHEN
        Mockito.when(pricesService.getRatesByApplicationDate(productId, brandId, time)).thenThrow(new ValidationRatesException(er.nextObject(RateError.class)));

        //THEN

        ResponseEntity resp = pricesControllerTest.getRatesByApplicationDate(productId, brandId, time);
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, resp.getStatusCode());

    }

}
