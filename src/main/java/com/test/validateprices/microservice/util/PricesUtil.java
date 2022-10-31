package com.test.validateprices.microservice.util;

import com.test.validateprices.microservice.model.Prices;

import java.util.Comparator;
import java.util.List;

public class PricesUtil {

    public static Prices getProirityRate  (List<Prices> list){
        list.sort(Comparator.comparing(Prices::getPriority));
        return list.get(list.size()-1);
    }

}
