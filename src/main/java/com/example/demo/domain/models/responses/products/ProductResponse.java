package com.example.demo.domain.models.responses.products;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private String brand;
    private String code;
}
