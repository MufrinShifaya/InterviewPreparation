package org.example.careercompassai.controller;

import lombok.RequiredArgsConstructor;
import org.example.careercompassai.dto.LoginRequest;
import org.example.careercompassai.dto.RegisterRequest;
import org.example.careercompassai.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}