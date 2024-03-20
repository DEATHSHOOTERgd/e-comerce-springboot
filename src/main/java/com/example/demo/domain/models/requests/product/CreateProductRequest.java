package com.example.demo.domain.models.requests.product;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateProductRequest {
    private String name;
    private Double price;
    private String description;
    private String brand;
    private String code;
}
