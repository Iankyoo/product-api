package com.iankyoo.product_api.service;

import com.iankyoo.product_api.dto.AuthResponse;
import com.iankyoo.product_api.dto.LoginRequest;
import com.iankyoo.product_api.dto.RegisterRequest;
import com.iankyoo.product_api.entity.Role;
import com.iankyoo.product_api.entity.User;
import com.iankyoo.product_api.exception.UserNotFoundException;
import com.iankyoo.product_api.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;

    public AuthService(JwtService jwtService, PasswordEncoder passwordEncoder, UserRepository repository){
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
    }

    public AuthResponse registerUser(RegisterRequest request){
        User newUser = repository.save(User.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build());

        String token = jwtService.generateToken(newUser);
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request){
        User user = repository.findByEmail(request.email())
                .orElseThrow(() -> new UserNotFoundException(request.email()));

        if (!passwordEncoder.matches(request.password(), user.getPassword())){
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }
}
