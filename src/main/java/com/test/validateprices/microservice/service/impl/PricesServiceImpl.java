package com.test.validateprices.microservice.service.impl;

import com.test.validateprices.microservice.bean.ValidationRateResponse;
import com.test.validateprices.microservice.exception.RateError;
import com.test.validateprices.microservice.exception.ValidationRatesException;
import com.test.validateprices.microservice.mapper.PricesMapper;
import com.test.validateprices.microservice.model.Prices;
import com.test.validateprices.microservice.repository.PricesRepository;
import com.test.validateprices.microservice.service.PricesService;
import com.test.validateprices.microservice.util.PricesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PricesServiceImpl implements PricesService {

    Logger logger = Logger.getLogger(PricesServiceImpl.class.getName());
    @Autowired
    private PricesRepository pricesRepository;

    @Override
    public ValidationRateResponse getRatesByApplicationDate(Long productId, Long brandId, Timestamp applicationDate) throws ValidationRatesException {
        logger.log(Level.parse("INFO"),"getRatesByApplicationDate("+productId+","+brandId+","+applicationDate+") start()");
        List<Prices> pricesList = pricesRepository.findRateByApplicationDate(productId,brandId,applicationDate);
        if(pricesList.size() == 0){
            throw new ValidationRatesException( new RateError(500, "Internat Server Error",
                    "Operation not allowed"));
        }
        Prices finalPrice = PricesUtil.getProirityRate(pricesList);
        ValidationRateResponse response = PricesMapper.INSTANCIA.pricesToValidationRateResponse(finalPrice);

        logger.log(Level.parse("INFO"),"getRatesByApplicationDate("+productId+","+brandId+","+applicationDate+") finished()");

        return response;
    }

}
