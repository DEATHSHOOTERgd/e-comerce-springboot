package com.example.demo.infrastructure.database.product;

import com.example.demo.domain.entitites.products.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductReactiveCRUDRepository extends ReactiveCrudRepository<Product, Long> {
}
