package com.example.authentication.auth;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "auth-service", url = "http://localhost:8100")
public interface AuthServiceProxy {

    @GetMapping("/database-service-user/{email}")
    Optional<User> getByEmail(@PathVariable String email);

}
