package org.example.service;

import org.example.exception.ProductNotFoundException;
import org.example.model.Product;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{


    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void save(Product product) {
        if (productRepository.findByProductCode(product.getProductCode()).isPresent()) {
            throw new ProductNotFoundException(String.format("Product already exists in the database with productCode %s", product.getProductCode()));
        } else {
            productRepository.save(product);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductByCode(String productCode) {
        return productRepository.findByProductCode(productCode)
                .orElseThrow(() -> new ProductNotFoundException(String.format("Product with code %s doesn't exist", productCode)));
    }

    @Override
    public void updateProduct(String productCode, Product product) {
        Product optionalProduct = productRepository.findByProductCode(productCode)
                .orElseThrow(() -> new ProductNotFoundException(String.format("Product with code %s doesn't exist", productCode)));

        optionalProduct.setName(product.getName());
        optionalProduct.setDescription(product.getName());
        optionalProduct.setPrice(product.getPrice());
        optionalProduct.setStockCount(product.getStockCount());
        productRepository.save(optionalProduct);
    }

    @Override
    public void deleteProduct(String productCode) {
        Product optionalProduct = productRepository.findByProductCode(productCode)
                .orElseThrow(() -> new ProductNotFoundException(String.format("Product with code %s doesn't exist", productCode)));

        productRepository.deleteByProductCode(optionalProduct.getProductCode());
    }


}
