package com.example.catalog.controller;

import com.example.catalog.model.Product;
import com.example.catalog.proxy.DatabaseServerProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/catalog-service-product")
public class CatalogController {

    @Autowired
    private DatabaseServerProxy proxy;

    @GetMapping("/{name}")
    public Product getProduct(@PathVariable String name) {

        Map<String, String> variables = new HashMap();
        variables.put("name", name);
        ResponseEntity<Product> request = new RestTemplate()
                .getForEntity("http://localhost:8100/database-service-product/{name}", Product.class, variables);
        return request.getBody();
    }

    @GetMapping("/feign/{name}")
    public Product getProductFeign(@PathVariable String name) {
        return proxy.getProduct(name);
    }
}
