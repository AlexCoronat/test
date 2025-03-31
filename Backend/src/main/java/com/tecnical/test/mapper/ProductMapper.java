package com.tecnical.test.mapper;

import com.tecnical.test.dto.ProductDTO;
import com.tecnical.test.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toProductDTO(Product product);
    Product toProduct(ProductDTO productDTO);
    List<ProductDTO> toProductDTOList(List<Product> productList);
}
