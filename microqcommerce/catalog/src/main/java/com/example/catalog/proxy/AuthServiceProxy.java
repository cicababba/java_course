package com.example.catalog.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-service", url = "http://localhost:8200")
public interface AuthServiceProxy {

    @GetMapping("/auth/validate")
    String validateToken(@RequestHeader("Authorization") String token);
}
