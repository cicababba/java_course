package com.example.authentication.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody AuthDTO authDTO) {
        return authService.login(authDTO.getEmail(), authDTO.getPassword());
    }

    @GetMapping("/validate")
    public String login(@RequestHeader("Authorization") String token) {
        return authService.validateToken(token).getToken();
    }

}
