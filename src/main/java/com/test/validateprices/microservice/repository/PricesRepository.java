package com.test.validateprices.microservice.repository;

import com.test.validateprices.microservice.model.Prices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface PricesRepository extends JpaRepository<Prices,Long>  {

    @Query("select l from Prices l where l.productId = ?1 and l.brandId = ?2 and ?3 between l.startDate and l.endDate")
    List<Prices> findRateByApplicationDate(Long productId, Long brandId, Timestamp applicationDate);

}
