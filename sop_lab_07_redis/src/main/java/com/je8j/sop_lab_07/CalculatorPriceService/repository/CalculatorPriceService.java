package com.je8j.sop_lab_07.CalculatorPriceService.repository;

import org.springframework.stereotype.Service;

@Service
public class CalculatorPriceService {

    public double getPrice(double productCost, double productProfit){
        return productCost + productProfit;
    }
}
