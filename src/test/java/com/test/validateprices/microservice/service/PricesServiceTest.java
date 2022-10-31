package com.test.validateprices.microservice.service;

import com.test.validateprices.microservice.bean.ValidationRateResponse;
import com.test.validateprices.microservice.model.Prices;
import com.test.validateprices.microservice.repository.PricesRepository;
import com.test.validateprices.microservice.service.impl.PricesServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan(basePackages = "com.test.validateprices.microservice.")
class PricesServiceTest {

    @Autowired
    private PricesServiceImpl pricesService;

    @Autowired
    private PricesRepository pricesRepository;

    @BeforeEach
    void populatePrices() throws ParseException {
        //POPULATE DATA FOR TEST
        Prices price = new Prices();
        price.setPkId(0L);
        price.setBrandId(1L);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-d HH:mm:ss");
        Date date = dateFormat.parse("2020-06-14 00:00:00");
        price.setStartDate(new Timestamp(date.getTime()));
        date = dateFormat.parse("2020-12-31 23:59:59");
        price.setEndDate(new Timestamp(date.getTime()));
        price.setPriceList(1);
        price.setProductId(35455L);
        price.setPriority(0);
        price.setPrice(35.50);
        price.setCurrency("EUR");
        pricesRepository.save(price);
        price.setPkId(1L);
        price.setBrandId(1L);
        date = dateFormat.parse("2020-06-14 15:00:00");
        price.setStartDate(new Timestamp(date.getTime()));
        date = dateFormat.parse("2020-06-14 18:30:00");
        price.setEndDate(new Timestamp(date.getTime()));
        price.setPriceList(2);
        price.setProductId(35455L);
        price.setPriority(1);
        price.setPrice(25.45);
        price.setCurrency("EUR");
        pricesRepository.save(price);
        price.setPkId(2L);
        price.setBrandId(1L);
        date = dateFormat.parse("2020-06-15 00:00:00");
        price.setStartDate(new Timestamp(date.getTime()));
        date = dateFormat.parse("2020-06-15 11:00:00");
        price.setEndDate(new Timestamp(date.getTime()));
        price.setPriceList(3);
        price.setProductId(35455L);
        price.setPriority(1);
        price.setPrice(30.50);
        price.setCurrency("EUR");
        pricesRepository.save(price);
        price.setPkId(3L);
        price.setBrandId(1L);
        date = dateFormat.parse("2020-06-15 16:00:00");
        price.setStartDate(new Timestamp(date.getTime()));
        date = dateFormat.parse("2020-12-31 23:59:59");
        price.setEndDate(new Timestamp(date.getTime()));
        price.setPriceList(4);
        price.setProductId(35455L);
        price.setPriority(1);
        price.setPrice(38.95);
        price.setCurrency("EUR");
        pricesRepository.save(price);
    }

    @Test
    void test1_2020_06_14_10_00_00() throws ParseException {
        //GIVEN productId:35455 brandId: 1 applicationDate: 2020-06-14 10:00:00
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-d HH:mm:ss");
        Date date = dateFormat.parse("2020-06-14 10:00:00");
        //WHEN
        ValidationRateResponse response = pricesService.getRatesByApplicationDate(35455L,1L,new Timestamp(date.getTime()));
        //THEN expected price: 35.5 EUR
        Assert.assertEquals("35.5 EUR",response.getFinalPrice());
    }
    @Test
    void test2_2020_06_14_16_00_00() throws ParseException {
        //GIVEN productId:35455 brandId: 1 applicationDate: 2020-06-14 16:00:00
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-d HH:mm:ss");
        Date date = dateFormat.parse("2020-06-14 16:00:00");
        //WHEN
        ValidationRateResponse response = pricesService.getRatesByApplicationDate(35455L,1L,new Timestamp(date.getTime()));
        //THEN expected price: 25.45 EUR
        Assert.assertEquals("25.45 EUR",response.getFinalPrice());
    }
    @Test
    void test3_2020_06_14_21_00_00() throws ParseException {
        //GIVEN productId:35455 brandId: 1 applicationDate: 2020-06-14 21:00:00
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-d HH:mm:ss");
        Date date = dateFormat.parse("2020-06-14 21:00:00");
        //WHEN
        ValidationRateResponse response = pricesService.getRatesByApplicationDate(35455L,1L,new Timestamp(date.getTime()));
        //THEN expected price: 35.5 EUR
        Assert.assertEquals("35.5 EUR",response.getFinalPrice());
    }
    @Test
    void test4_2020_06_15_10_00_00() throws ParseException {
        //GIVEN productId:35455 brandId: 1 applicationDate: 2020-06-15 10:00:00
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-d HH:mm:ss");
        Date date = dateFormat.parse("2020-06-15 10:00:00");
        //WHEN
        ValidationRateResponse response = pricesService.getRatesByApplicationDate(35455L,1L,new Timestamp(date.getTime()));
        //THEN expected price: 30.5 EUR
        Assert.assertEquals("30.5 EUR",response.getFinalPrice());
    }
    @Test
    void test5_2020_06_16_21_00_00() throws ParseException {
        //GIVEN productId:35455 brandId: 1 applicationDate: 2020-06-16 21:00:00
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-d HH:mm:ss");
        Date date = dateFormat.parse("2020-06-16 21:00:00");
        //WHEN
        ValidationRateResponse response = pricesService.getRatesByApplicationDate(35455L,1L,new Timestamp(date.getTime()));
        //THEN expected price: 35.5 EUR
        Assert.assertEquals("38.95 EUR",response.getFinalPrice());
    }
}