package com.kaiba.product_catalog.service;

import com.kaiba.product_catalog.dto.AuthResponse;
import com.kaiba.product_catalog.dto.RegisterRequest;
import com.kaiba.product_catalog.entity.User;
import com.kaiba.product_catalog.repository.UserRepository;
import com.kaiba.product_catalog.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);
        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(token);
    }
}
