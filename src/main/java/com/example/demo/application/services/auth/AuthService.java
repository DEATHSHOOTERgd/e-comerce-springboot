package com.example.demo.application.services.auth;

import com.example.demo.application.interfaces.services.auth.IAuthService;
import com.example.demo.application.utils.EncryptionUtils;
import com.example.demo.application.utils.JWTUtils;
import com.example.demo.domain.entitites.auth.User;
import com.example.demo.domain.models.exceptions.AuthException;
import com.example.demo.domain.models.exceptions.ClientFaultException;
import com.example.demo.domain.models.exceptions.ServerFaultException;
import com.example.demo.domain.models.requests.auth.LoginRequest;
import com.example.demo.domain.models.requests.auth.RegisterRequest;
import com.example.demo.domain.models.responses.auth.LoginResponse;
import com.example.demo.domain.models.responses.auth.RegisterResponse;
import com.example.demo.infrastructure.repositories.auth.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AuthService implements IAuthService {
    @Autowired
    private EncryptionUtils encryptionUtils;
    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private JWTUtils jwtUtils;

    @Override
    public Mono<LoginResponse> login(LoginRequest loginRequest) {
        var userData = authRepository.getUserByUsername(loginRequest.getUsername());

        return userData.map(user->{
            if(!encryptionUtils.validatePassword(loginRequest.getPassword(), user.getPassword())){
                throw new AuthException("Usuario o contraseña incorrectos.");
            }

            return new LoginResponse(
                    user.getUsername(),
                    user.getName(),
                    user.getLastName(),
                    user.getEmail(),
                    jwtUtils.generateToken(user.getUsername(), "USER", user.getId())
            );
        }).switchIfEmpty(Mono.error(new AuthException("Usuario o contraseña incorrectos.")));
    }

    @Override
    public Mono<RegisterResponse> register(RegisterRequest registerRequest) {
        var userData = authRepository.getUserByUsername(registerRequest.getUsername());

        var createdUserMono = userData.flatMap(user->Mono.error(new ClientFaultException("Nombre de usuario no disponible"))).switchIfEmpty(
                authRepository.createUser(new User(
                        registerRequest.getUsername(),
                        encryptionUtils.hashPassword(registerRequest.getPassword()),
                        registerRequest.getName(),
                        registerRequest.getLastName(),
                        registerRequest.getEmail(),
                        null,
                        true
                ))
        );

        return createdUserMono.map(createduser-> new RegisterResponse());
    }
}
