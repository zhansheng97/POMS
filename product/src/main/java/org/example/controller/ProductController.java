package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.model.Product;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/save")
    public void saveProduct(@RequestBody Product product) {
        productService.save(product);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{productCode}")
    public ResponseEntity<Product> getProduct(@PathVariable("productCode") String productCode) {
        Product product = productService.getProductByCode(productCode);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/update/{productCode}")
    public void updateProduct(@PathVariable("productCode") String productCode, @RequestBody Product product) {
        productService.updateProduct(productCode,product);
    }

    @DeleteMapping("/delete/{productCode}")
    public void deleteProduct(@PathVariable("productCode") String productCode) {
        productService.deleteProduct(productCode);

    }


}
