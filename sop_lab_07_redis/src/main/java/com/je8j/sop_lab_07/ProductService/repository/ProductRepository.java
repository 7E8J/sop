package com.je8j.sop_lab_07.ProductService.repository;

import com.je8j.sop_lab_07.ProductService.pojo.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    @Query(value = "{productName : '?0'}")
    public Product findByName(String name);
}
