package com.test.validateprices.microservice.controller;

import com.test.validateprices.microservice.Constants.ApiConstants;
import com.test.validateprices.microservice.bean.ValidationRateRequest;
import com.test.validateprices.microservice.bean.ValidationRateResponse;
import com.test.validateprices.microservice.exception.RateError;
import com.test.validateprices.microservice.exception.ValidationRatesException;
import com.test.validateprices.microservice.model.Prices;
import com.test.validateprices.microservice.service.PricesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.Timestamp;

@RestController
@Tag(name = ApiConstants.TAG_RATE, description = ApiConstants.DESCRIPTION_RATE)
public class PricesController {
    @Autowired
    private PricesService pricesService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Prices.class)))),
            @ApiResponse(responseCode = "400", description = "bad request operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = RateError.class))))})
    @GetMapping(value = "/validateRate", produces = "application/json")
    public ResponseEntity<ValidationRateResponse> getRatesByApplicationDate(@Parameter Long productId,
                                                                            @Parameter Long brandId,
                                                                            @Parameter Timestamp applicationDate) {
        try{
        ValidationRateResponse response = pricesService.getRatesByApplicationDate(productId,brandId,applicationDate);
        return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (ValidationRatesException e) {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }
}
