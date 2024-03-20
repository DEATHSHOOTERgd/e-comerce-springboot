package com.example.demo.application.interfaces.repositories.auth;

import com.example.demo.domain.entitites.auth.User;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface IAuthRepository {
    Mono<User> getUserByUsername(String username);
    Mono<User> createUser(User user);
}
