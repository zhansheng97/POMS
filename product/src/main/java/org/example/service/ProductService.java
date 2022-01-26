package org.example.service;

import org.example.model.Product;

import java.util.List;

public interface ProductService {

    void save(Product product);
    List<Product> getAllProducts();
    Product getProductByCode(String productCode);
    void updateProduct(String productCode, Product product);
    void deleteProduct(String productCode);

}
