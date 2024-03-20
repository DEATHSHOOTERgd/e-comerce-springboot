package com.example.demo.application.interfaces.services.products;

import com.example.demo.domain.models.requests.product.CreateProductRequest;
import com.example.demo.domain.models.responses.products.ProductResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public interface IProductService {
    Mono<ProductResponse> getProductById(Long id);
    Mono<List<ProductResponse>> getProducts();
    Mono<ProductResponse> createProduct(CreateProductRequest product);
}
