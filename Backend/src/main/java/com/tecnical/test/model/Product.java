package com.tecnical.test.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product", length = 6)
    private Integer idProduct;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false, scale = 2, precision = 16)
    private BigDecimal price;
    private Integer stock;
    @Column(length = 1)
    private boolean status;
}
