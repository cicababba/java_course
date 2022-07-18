package com.example.catalog.controller;

import com.example.catalog.model.Product;
import com.example.catalog.proxy.AuthServiceProxy;
import com.example.catalog.proxy.DatabaseServerProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/catalog-service-product")
public class CatalogController {

    @Autowired
    private DatabaseServerProxy proxy;

    @Autowired
    private AuthServiceProxy authServiceProxy;

    @GetMapping("/{name}")
    public Product getProductFeign(@PathVariable String name, @RequestHeader("Authorization") String token) {
        String result = authServiceProxy.validateToken(token);
        System.out.println(result);
        return proxy.getProduct(name);
    }
}
