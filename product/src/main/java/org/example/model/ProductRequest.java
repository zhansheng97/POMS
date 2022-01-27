package org.example.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = ProductRequest.class)
public class ProductRequest {

    private String productCode;
    private Integer quantity;

    public ProductRequest() {
    }

    public ProductRequest(String productCode, Integer quantity) {
        this.productCode = productCode;
        this.quantity = quantity;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductRequest{" +
                "productCode='" + productCode + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}
