package com.je8j.sop_lab_07.CalculatorPriceService.controller;

import com.je8j.sop_lab_07.CalculatorPriceService.repository.CalculatorPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorPriceController {
    private final CalculatorPriceService calculatorService;

    @Autowired
    private CalculatorPriceController(CalculatorPriceService calculatorService1) {
        this.calculatorService = calculatorService1;
    }

    @RequestMapping(value = "/getPrice/{cost}/{price}", method = RequestMethod.GET)
    public double serviceGetProducts(@PathVariable double cost,@PathVariable double price){
        return calculatorService.getPrice(cost, price);
    }
}
