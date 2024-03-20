package com.example.demo.infrastructure.database.auth;

import com.example.demo.domain.entitites.auth.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserReactiveCRUDRepository extends ReactiveCrudRepository<User, Long> {
    public Flux<User> findByUsername(String username);
}
