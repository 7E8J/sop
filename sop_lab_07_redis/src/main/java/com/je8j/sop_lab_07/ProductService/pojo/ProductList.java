package com.je8j.sop_lab_07.ProductService.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProductList implements Serializable {
    private List<Product> model;
}
