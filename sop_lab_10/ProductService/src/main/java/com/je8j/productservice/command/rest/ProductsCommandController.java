package com.je8j.productservice.command.rest;

import com.je8j.productservice.command.CreateProductCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/commandProducts")
public class ProductsCommandController {
    private final Environment env;
    private final CommandGateway commandGateway;

    @Autowired
    public ProductsCommandController(Environment env, CommandGateway commandGateway) {
        this.env = env;
        this.commandGateway = commandGateway;
    }

    @GetMapping
    public String getProduct(){
        return "Http GET Handled "+ env.getProperty("local.server.port");
    }

    @PostMapping
    public String createProduct(@RequestBody CreateProductRestModel model){
        CreateProductCommand command = CreateProductCommand.builder()
                .productId(UUID.randomUUID().toString())
                .title(model.getTitle())
                .price(model.getPrice())
                .quantity(model.getQuantity())
                .build();

        String result ;
        try{
            result = commandGateway.sendAndWait(command);
        } catch(Exception e) {
            result = e.getLocalizedMessage();
        }

        return result;
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