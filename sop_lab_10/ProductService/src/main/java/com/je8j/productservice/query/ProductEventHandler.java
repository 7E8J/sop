package com.je8j.productservice.query;


import com.je8j.productservice.core.data.ProductEntity;
import com.je8j.productservice.core.data.ProductRepository;
import com.je8j.productservice.core.events.ProductCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
@Component
public class ProductEventHandler {

    private  final ProductRepository productRepository;
    public ProductEventHandler(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @EventHandler
    public void on(ProductCreatedEvent event){
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(event, productEntity);
        productRepository.save(productEntity);
    }
}
