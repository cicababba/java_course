package com.example.database.controller;


import com.example.database.model.User;
import com.example.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/database-service-user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{email}")
    public User getByEmail(@PathVariable String email) {
           return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
