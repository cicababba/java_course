package com.example.catalog.proxy;

import com.example.catalog.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "database-service", url = "http://localhost:8100")
public interface DatabaseServerProxy {

    @GetMapping("/database-service-product/{name}")
    Product getProduct(@PathVariable String name);
}
