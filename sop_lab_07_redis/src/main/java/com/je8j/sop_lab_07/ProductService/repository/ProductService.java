package com.je8j.sop_lab_07.ProductService.repository;

import com.je8j.sop_lab_07.ProductService.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @CachePut(value="product", key="#product.getProductName()")
    @CacheEvict(value="productList", allEntries=true)
    public Boolean addProduct(Product product) {
        try {
            productRepository.save(product);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @CachePut(value="product", key="#product.getProductName()")
    @CacheEvict(value="productList", allEntries=true)
    public Boolean updateProduct(Product product) {
        try {
            productRepository.save(product);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @CacheEvict(value="product", key="#product.getProductName()")
    @Caching(evict={ @CacheEvict(value="productList", allEntries=true) })
    public Boolean deleteProduct(Product product) {
        try {
            productRepository.delete(product);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Cacheable(value="productList")
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    @Cacheable(value="product", key="#name")
    public Product getProductByName(String name){
        return productRepository.findByName(name);
    }
}
