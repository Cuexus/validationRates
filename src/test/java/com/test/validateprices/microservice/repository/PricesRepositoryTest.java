package com.test.validateprices.microservice.repository;

import com.test.validateprices.microservice.model.Prices;
import org.junit.Assert;
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
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan(basePackages = "com.test.validateprices.microservice.")
class PricesRepositoryTest {

    @Autowired
    private PricesRepository pricesRepository;

    @Test
    void findRateByApplicationDate() throws ParseException {
        //GIVEN
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
        date = dateFormat.parse("2020-06-14 10:00:00");

        //THEN

        List<Prices> list = pricesRepository.findRateByApplicationDate(35455L,1L,new Timestamp(date.getTime()));
        Assert.assertNotNull(list);
    }
}