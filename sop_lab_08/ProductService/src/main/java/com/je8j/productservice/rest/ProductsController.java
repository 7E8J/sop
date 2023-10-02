package com.je8j.productservice.rest;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private final Environment env;
    public ProductsController(Environment env){
        this.env = env;
    }

    @GetMapping
    public String getProduct(){
        return "Http GET Handled "+ env.getProperty("local.server.port");
    }

    @PostMapping
    public String createProduct(){
        return "Http POST Handled";
    }

    @PutMapping
    public String updateProduct(){
        return "Http PUT Handled";
    }

    @DeleteMapping
    public String deleteProduct(){
        return "Http DELETE Handled";
    }
}