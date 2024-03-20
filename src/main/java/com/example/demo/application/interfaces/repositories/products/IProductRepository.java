package com.example.demo.application.interfaces.repositories.products;

import com.example.demo.domain.entitites.products.Product;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface IProductRepository {
    Mono<Product> getProductById(Long productId);
    Flux<Product> getProducts();
    Mono<Product> createProduct(Product product);
}
