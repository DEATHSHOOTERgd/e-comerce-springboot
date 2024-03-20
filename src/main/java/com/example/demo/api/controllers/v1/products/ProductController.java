package com.example.demo.api.controllers.v1.products;

import com.example.demo.api.extentions.RequestContextAccessor;
import com.example.demo.application.interfaces.services.auth.IAuthService;
import com.example.demo.application.interfaces.services.products.IProductService;
import com.example.demo.domain.models.requests.auth.LoginRequest;
import com.example.demo.domain.models.requests.auth.RegisterRequest;
import com.example.demo.domain.models.requests.product.CreateProductRequest;
import com.example.demo.domain.models.responses.auth.LoginResponse;
import com.example.demo.domain.models.responses.auth.RegisterResponse;
import com.example.demo.domain.models.responses.general.CustomResponse;
import com.example.demo.domain.models.responses.products.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {

    @Autowired
    private IProductService productService;
    @Autowired
    private RequestContextAccessor requestContextAccessor;

    @GetMapping("/{id}")
    public Mono<ResponseEntity<CustomResponse<ProductResponse>>> getProductById(@PathVariable Long id){
        var productResponse = productService.getProductById(id);

        return Mono.zip(productResponse, requestContextAccessor.getTraceId()).map(responses-> ResponseEntity.status(HttpStatus.OK)
                .body(new CustomResponse<ProductResponse>(responses.getT1(), 200, "", responses.getT2())));
    }

    @GetMapping("/")
    public Mono<ResponseEntity<CustomResponse<List<ProductResponse>>>> getProducts(){
        var productResponse = productService.getProducts();

        return Mono.zip(productResponse, requestContextAccessor.getTraceId()).map(responses-> ResponseEntity.status(HttpStatus.OK)
                .body(new CustomResponse<List<ProductResponse>>(responses.getT1(), 200, "", responses.getT2())));
    }
    @PostMapping("/")
    public Mono<ResponseEntity<CustomResponse<ProductResponse>>> getProductById(@RequestBody CreateProductRequest createProductRequest){
        var productResponse = productService.createProduct(createProductRequest);

        return Mono.zip(productResponse, requestContextAccessor.getTraceId()).map(responses-> ResponseEntity.status(HttpStatus.OK)
                .body(new CustomResponse<ProductResponse>(responses.getT1(), 200, "Producto ingresado.", responses.getT2())));
    }
}
