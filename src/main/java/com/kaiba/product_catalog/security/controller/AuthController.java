package com.kaiba.product_catalog.security.controller;

import com.kaiba.product_catalog.dto.RegisterRequest;
import com.kaiba.product_catalog.dto.AuthResponse;
import com.kaiba.product_catalog.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
}

