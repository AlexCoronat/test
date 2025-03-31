package com.tecnical.test.service;

import com.tecnical.test.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAll();
    ProductDTO getById(Integer id_product);
    ProductDTO save(ProductDTO productDTO);
    ProductDTO update(Integer id_product,ProductDTO productDTO);
}
