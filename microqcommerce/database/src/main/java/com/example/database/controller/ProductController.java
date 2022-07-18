package com.example.database.controller;

import com.example.database.model.Product;
import com.example.database.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/database-service-product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

//    @GetMapping("/{id}")
//    public Product getProductById(@PathVariable long id) {
//        Optional<Product> optProduct = productRepository.findById(id);
//        if(optProduct.isEmpty()) {
//            throw new IllegalArgumentException("Product not found");
//        }
//        return optProduct.get();
//    }

    @GetMapping("/{name}")
    public Product getProductByName(@PathVariable String name) {
        Optional<Product> optProduct = productRepository.findByName(name);
        if(optProduct.isEmpty()) {
            throw new IllegalArgumentException("Product not found");
        }
        return optProduct.get();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {

        if(productRepository.findByNameAndDescription(product.getName(), product.getDescription()).isPresent())
            throw new RuntimeException("Product already exists");
        return productRepository.save(product);
    }
}
