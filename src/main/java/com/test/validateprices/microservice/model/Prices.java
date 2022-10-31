package com.test.validateprices.microservice.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Prices {

    @Id
    private Long pkId;
    private Long brandId;
    private Timestamp startDate;
    private Timestamp endDate;
    private int priceList;
    private Long productId;
    private int priority;
    private Double price;
    private String currency;

    public String finalPrice() {
        return this.price + " " + this.currency;
    }
}
