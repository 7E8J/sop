package com.example.sop_lab_04;

import org.springframework.web.bind.annotation.*;

@RestController
public class MathAPI {
    @GetMapping(value = "/plus/{num1}/{num2}")
    public double myPlus (@PathVariable("num1") double num1, @PathVariable("num2") double num2) {
        return num1 + num2;
    }

    @GetMapping(value = "/minus/{num1}/{num2}")
    public double myMinus (@PathVariable("num1") double num1, @PathVariable("num2") double num2) {
        return num1 - num2;
    }

    @GetMapping(value = "/multi/{num1}/{num2}")
    public double myMulti (@PathVariable("num1") double num1, @PathVariable("num2") double num2) {
        return num1 * num2;
    }

    @GetMapping(value = "/divide/{num1}/{num2}")
    public double myDivide (@PathVariable("num1") double num1, @PathVariable("num2") double num2) {
        return num1 / num2;
    }

    @GetMapping(value = "/mod/{num1}/{num2}")
    public double myMod (@PathVariable("num1") double num1, @PathVariable("num2") double num2) {
        return num1 % num2;
    }

    @PostMapping(value = "/max")
    public double myMax (@RequestParam("num1") double num1, @RequestParam("num2") double num2) {
        if(num1 >= num2){
            return num1;
        } else {
            return num2;
        }
    }

}
