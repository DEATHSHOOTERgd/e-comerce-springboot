package com.example.demo.domain.entitites.products;

import com.example.demo.domain.entitites.general.Base;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;


@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Table("product")
public class Product extends Base {
    private String name;
    private Double price;
    private String description;
    private String brand;
    private String code;
}
