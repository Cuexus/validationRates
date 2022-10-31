package com.test.validateprices.microservice.service;

import com.test.validateprices.microservice.bean.ValidationRateResponse;
import com.test.validateprices.microservice.exception.RateError;
import com.test.validateprices.microservice.exception.ValidationRatesException;
import com.test.validateprices.microservice.model.Prices;
import com.test.validateprices.microservice.repository.PricesRepository;
import com.test.validateprices.microservice.util.PricesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PricesService {

    Logger logger = Logger.getLogger(PricesService.class.getName());
    @Autowired
    private PricesRepository pricesRepository;

    public ValidationRateResponse getRatesByApplicationDate(Long productId, Long brandId, Timestamp applicationDate) throws ValidationRatesException {
        logger.log(Level.parse("INFO"),"getRatesByApplicationDate("+productId+","+brandId+","+applicationDate+") start()");
        List<Prices> pricesList = pricesRepository.findRateByApplicationDate(productId,brandId,applicationDate);
        if(pricesList.size() == 0){
            throw new ValidationRatesException( new RateError(400, "Bad Request",
                    "Operation not allowed"));
        }
        Prices finalPrice = PricesUtil.getProirityRate(pricesList);
        ValidationRateResponse response = PricesUtil.mappingRatesResponse(finalPrice);

        logger.log(Level.parse("INFO"),"getRatesByApplicationDate("+productId+","+brandId+","+applicationDate+") finished()");

        return response;
    }

}
