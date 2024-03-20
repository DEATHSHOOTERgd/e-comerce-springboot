package com.example.demo.application.services.products;

import com.example.demo.application.interfaces.repositories.products.IProductRepository;
import com.example.demo.application.interfaces.services.products.IProductService;
import com.example.demo.domain.entitites.products.Product;
import com.example.demo.domain.models.exceptions.ClientFaultException;
import com.example.demo.domain.models.requests.product.CreateProductRequest;
import com.example.demo.domain.models.responses.products.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;
    @Override
    public Mono<ProductResponse> getProductById(Long id) {
        return productRepository.getProductById(id).map(product -> new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getBrand(),
                product.getCode()
                )
        ).switchIfEmpty(Mono.error(new ClientFaultException("Producto no Existe.",404)));
    }

    @Override
    public Mono<List<ProductResponse>> getProducts() {
        return productRepository.getProducts().collectList().map(products->products.stream().map(product -> new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getBrand(),
                product.getCode()
        )).collect(Collectors.toList()));
    }

    @Override
    public Mono<ProductResponse> createProduct(CreateProductRequest product) {
        return productRepository.createProduct(new Product(
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getBrand(),
                product.getCode()
        )).map(createdProduct -> new ProductResponse(
                    createdProduct.getId(),
                    createdProduct.getName(),
                    createdProduct.getPrice(),
                    createdProduct.getDescription(),
                    createdProduct.getBrand(),
                    createdProduct.getCode()
                )
        );
    }
}
