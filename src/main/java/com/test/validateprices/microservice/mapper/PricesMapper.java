package com.test.validateprices.microservice.mapper;

import com.test.validateprices.microservice.bean.ValidationRateResponse;
import com.test.validateprices.microservice.model.Prices;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PricesMapper {

    PricesMapper INSTANCIA= Mappers.getMapper(PricesMapper.class);

    @Mapping(target="productId", source="prices.productId")
    @Mapping(target="brandId", source="prices.brandId")
    @Mapping(target="priceList", source="prices.priceList")
    @Mapping(target="startDate", source="prices.startDate")
    @Mapping(target="endDate", source="prices.endDate")
    @Mapping(target="finalPrice", expression = "java(prices.getPrice() +  prices.getCurrency())")
    ValidationRateResponse pricesToValidationRateResponse  (Prices prices);

}
