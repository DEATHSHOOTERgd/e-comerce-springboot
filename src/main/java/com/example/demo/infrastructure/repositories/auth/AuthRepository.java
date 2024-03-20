package com.example.demo.infrastructure.repositories.auth;

import com.example.demo.application.interfaces.repositories.auth.IAuthRepository;
import com.example.demo.domain.entitites.auth.User;
import com.example.demo.infrastructure.database.auth.UserReactiveCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class AuthRepository implements IAuthRepository {
    @Autowired
    private UserReactiveCRUDRepository userRepository;

    @Override
    public Mono<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username).next().switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<User> createUser(User user) {
        return userRepository.save(user);
    }
}
