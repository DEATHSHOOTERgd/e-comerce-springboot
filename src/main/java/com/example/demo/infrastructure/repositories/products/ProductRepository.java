package com.example.demo.infrastructure.repositories.products;

import com.example.demo.application.interfaces.repositories.products.IProductRepository;
import com.example.demo.domain.entitites.products.Product;
import com.example.demo.infrastructure.database.product.ProductReactiveCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ProductRepository implements IProductRepository {

    @Autowired
    private ProductReactiveCRUDRepository productRepository;
    @Override
    public Mono<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Flux<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Mono<Product> createProduct(Product product) {
        return productRepository.save(product);
    }
}
