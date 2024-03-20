package com.example.demo.api.controllers.v1.auth;

import com.example.demo.api.extentions.RequestContextAccessor;
import com.example.demo.application.interfaces.services.auth.IAuthService;
import com.example.demo.domain.models.requests.auth.LoginRequest;
import com.example.demo.domain.models.requests.auth.RegisterRequest;
import com.example.demo.domain.models.responses.auth.LoginResponse;
import com.example.demo.domain.models.responses.auth.RegisterResponse;
import com.example.demo.domain.models.responses.general.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;
    @Autowired
    private RequestContextAccessor requestContextAccessor;

    @PostMapping("/login")
    public Mono<ResponseEntity<CustomResponse<LoginResponse>>> login(@RequestBody LoginRequest loginRequest){
        var loginResponse = authService.login(loginRequest);

        return Mono.zip(loginResponse, requestContextAccessor.getTraceId()).map(responses-> ResponseEntity.status(HttpStatus.OK)
                .body(new CustomResponse<LoginResponse>(responses.getT1(), 200, "Login exitoso.", responses.getT2())));
    }

    @PostMapping("/register")
    public Mono<ResponseEntity<CustomResponse<RegisterResponse>>> login(@RequestBody RegisterRequest registerRequest){
        var registerResponse = authService.register(registerRequest);

        return Mono.zip(registerResponse, requestContextAccessor.getTraceId()).map(responses-> ResponseEntity.status(HttpStatus.OK)
                .body(new CustomResponse<RegisterResponse>(responses.getT1(), 200, "Registro exitoso.", responses.getT2())));
    }
}
