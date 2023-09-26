package com.je8j.sop_lab_07.ProductService.controller;

import com.je8j.sop_lab_07.ProductService.pojo.Product;
import com.je8j.sop_lab_07.ProductService.repository.ProductService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;
    private RabbitTemplate rabbitmq;

    @Autowired
    private ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RabbitListener(queues = "AddProductQueue")
    public Boolean serviceAddProduct(Product product){
        try{
            return productService.addProduct(product);
        } catch (Exception e) {
            return false;
        }
    }

    @RabbitListener(queues = "UpdateProductQueue")
    public Boolean updateProduct(Product product) {
        try {
            Product existingProduct = productService.getProductByName(product.getProductName());

            if (existingProduct != null) {
                existingProduct.setProductName(product.getProductName());
                existingProduct.setProductCost(product.getProductCost());
                existingProduct.setProductProfit(product.getProductProfit());
                existingProduct.setProductPrice(product.getProductPrice());

                return productService.updateProduct(existingProduct);
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }


    @RabbitListener(queues = "DeleteProductQueue")
    public Boolean serviceDeleteProduct(Product product){
        try{
            return productService.deleteProduct(product);
        } catch (Exception e) {
            return false;
        }
    }

    @RabbitListener(queues = "GetNameProductQueue")
    public Product serviceGetProductByName(String p) {
        try{
            return productService.getProductByName(p);
        } catch (Exception e) {
            return null;
        }
    }

    @RabbitListener(queues = "GetAllProductQueue")
    public List<Product> serviceGetAllProduct(){
        try{
            return productService.getAllProduct();
        } catch (Exception e) {
            return null;
        }
    }
}
