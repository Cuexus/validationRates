package com.test.validateprices.microservice.controller;

import com.test.validateprices.microservice.bean.ValidationRateResponse;
import com.test.validateprices.microservice.model.Prices;
import com.test.validateprices.microservice.service.PricesService;
import org.jeasy.random.EasyRandom;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.text.ParseException;

@RunWith(SpringRunner.class)
public class PricesControllerTest {

    @InjectMocks
    PricesController pricesControllerTest;

    @Mock
    PricesService pricesService;

    EasyRandom er = new EasyRandom();

    @Test
    void findRateByApplicationDate() throws ParseException {
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
                .finalPrice(finalPrice.finalPrice()).build();
        //WHEN
        Mockito.when(pricesService.getRatesByApplicationDate(productId, brandId, time)).thenReturn(response);

        //THEN

        ResponseEntity resp = pricesControllerTest.getRatesByApplicationDate(productId, brandId, time);
        Assert.assertEquals(HttpStatus.ACCEPTED, resp.getStatusCode());


    }

}
