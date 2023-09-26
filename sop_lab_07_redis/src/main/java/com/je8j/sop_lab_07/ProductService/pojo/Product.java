package com.je8j.sop_lab_07.ProductService.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor; // เพิ่ม Import นี้
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Data
@Document("Product")
@NoArgsConstructor
public class Product implements Serializable {
    @Id
    private String _id;
    private String productName;
    private double productCost;
    private double productProfit;
    private double productPrice;

    public Product(String productName, double productCost, double productProfit, double productPrice) {
        this.productName = productName;
        this.productCost = productCost;
        this.productProfit = productProfit;
        this.productPrice = productPrice;
    }

}
