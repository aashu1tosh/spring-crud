package com.example.spring_crud.controller;

import com.example.spring_crud.dto.ProductDto;
import com.example.spring_crud.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.spring_crud.entity.Product;

import java.util.*;

@RestController
@RequestMapping("/product")
@Validated // To enable validation on the controller
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> addProduct(@Valid @RequestBody ProductDto productDto) {
        Map<String, Object> response = new HashMap<>();
        
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());

        // Save the product to the database
        productRepository.save(product);

        // If validation passed
        response.put("success", true);
        response.put("message", "Product added successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getAllProducts() {
        List<Product> products = productRepository.findAll();

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Products retrieved successfully");
        response.put("data", products);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Map<String, Object>> patchProduct(@PathVariable UUID id, @Valid @RequestBody ProductDto productDto) {
        Map<String, Object> response = new HashMap<>();

        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            // Update product fields
            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());

            // Save the updated product back to the database
            productRepository.save(product);
            response.put("success", true);
            response.put("message", "Product updated successfully");
            response.put("data", product);
            return ResponseEntity.ok(response);

        } else {
            response.put("success", false);
            response.put("message", "Product not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteProduct(@PathVariable UUID id) {
        Map<String, Object> response = new HashMap<>();

        // Check if the product exists
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            // Delete the product
            productRepository.deleteById(id);
            response.put("success", true);
            response.put("message", "Product deleted successfully");
            return ResponseEntity.ok(response);
        } else {
            // If product doesn't exist, return a 404 Not Found response
            response.put("success", false);
            response.put("message", "Product not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
