package com.example.demo.application.interfaces.services.auth;

import com.example.demo.domain.models.requests.auth.LoginRequest;
import com.example.demo.domain.models.requests.auth.RegisterRequest;
import com.example.demo.domain.models.responses.auth.LoginResponse;
import com.example.demo.domain.models.responses.auth.RegisterResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface IAuthService {
    Mono<LoginResponse> login(LoginRequest loginRequest);
    Mono<RegisterResponse> register(RegisterRequest registerRequest);
}
