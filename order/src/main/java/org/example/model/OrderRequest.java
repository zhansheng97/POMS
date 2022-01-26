package org.example.model;

import java.util.List;

public class OrderRequest {

    private String username;
    private String phoneNumber;
    private List<ProductRequest> products;

    public OrderRequest() {
    }

    public OrderRequest(String username, String phoneNumber, List<ProductRequest> products) {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.products = products;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<ProductRequest> getProducts() {
        return products;
    }

    public void setProducts(List<ProductRequest> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "username='" + username + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", products=" + products +
                '}';
    }
}
