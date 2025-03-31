package com.tecnical.test.controller;

import com.tecnical.test.dto.ProductDTO;
import com.tecnical.test.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductServiceImpl productService;
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("")
    public ResponseEntity<List<ProductDTO>> getProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAll());
    }

    @GetMapping("/{id_product}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Integer id_product) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getById(id_product));
    }

    @PostMapping("/new")
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO productDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    @PatchMapping("/{id_product}")
    public ResponseEntity<ProductDTO> update(@PathVariable Integer id_product, @RequestBody ProductDTO productDTO) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(productService.update(id_product, productDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
