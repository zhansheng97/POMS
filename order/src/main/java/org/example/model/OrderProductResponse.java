package org.example.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


public class OrderProductResponse {


    private String productCode;
    private Integer quantity;

    public OrderProductResponse(String productCode, Integer quantity) {
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
        return "OrderProductResponse{" +
                "productCode='" + productCode + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
