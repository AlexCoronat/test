package com.tecnical.test.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    private Integer idProduct;
    private String name;
    private BigDecimal price;
    private Integer stock;
    private boolean status;
}
