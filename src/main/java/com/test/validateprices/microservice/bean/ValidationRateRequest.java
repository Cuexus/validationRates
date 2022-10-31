package com.test.validateprices.microservice.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ValidationRateRequest {


    @JsonProperty("product_id")
    @Schema(name="product_id", description="Id of the product", type = "Long", defaultValue = "35455")
    private Long productId;
    @JsonProperty("brand_id")
    @Schema(name="brand_id", description="Id of the brand company", type = "int", defaultValue = "1")
    private int brandId;
    @JsonProperty("application_date")
    @Schema(name="application_date", description="Application Rate date", type = "Timestamp", defaultValue = "2020-06-15 21.00.00")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-d HH:mm:ss")
    private Timestamp applicationDate;

}
