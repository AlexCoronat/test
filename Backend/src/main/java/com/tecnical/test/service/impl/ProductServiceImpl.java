package com.tecnical.test.service.impl;

import com.tecnical.test.dto.ProductDTO;
import com.tecnical.test.mapper.ProductMapper;
import com.tecnical.test.model.History;
import com.tecnical.test.model.Product;
import com.tecnical.test.repository.ProductRepository;
import com.tecnical.test.service.HistoryService;
import com.tecnical.test.service.ProductService;
import com.tecnical.test.util.KeyPermissions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final PermissionService permissionService;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final KeyPermissions keyPermissions;
    private final HistoryService historyService;
    @Override
    public List<ProductDTO> getAll() {
        return productMapper.toProductDTOList(productRepository.findAll());
    }

    @Override
    public ProductDTO getById(Integer id_product) {
        return productMapper.toProductDTO(productRepository.findById(id_product).orElse(null));
    }
    @Override
    public ProductDTO save(ProductDTO productDTO){
        for(String permission : permissionService.getKeyPermissions()){
            log.info(permission);
        }
        if (!permissionService.getKeyPermissions().contains(keyPermissions.ADD_PRODUCT)) {
            throw new RuntimeException("No tiene permisos para esta operacion");
        }
        return productMapper.toProductDTO(productRepository.save(productMapper.toProduct(productDTO)));
    }
    @Override
    public ProductDTO update(Integer id_product, ProductDTO productDTO) {
        Product product;
        String type = "";
        if(id_product != null){
            product = productRepository.findById(id_product).orElse(null);
            if(product != null){
                if(productDTO.getName() != null){
                    product.setName(productDTO.getName());
                }
                if(productDTO.getPrice() != null){
                    product.setPrice(productDTO.getPrice());
                }
                if (productDTO.getStock() != null) {
                    if (productDTO.getStock() < product.getStock()) {
                        type = "Salida";
                    } else if (productDTO.getStock() > product.getStock()) {
                        type = "Entrada";
                    }
                    product.setStock(productDTO.getStock());
                    History history = new History();
                    history.setType(type);
                    history.setUser(permissionService.getUserDetails());
                    historyService.save(history);
                }
                    product.setStatus(productDTO.isStatus());
            }
        }else {
            product = productMapper.toProduct(productDTO);
        }
        return productMapper.toProductDTO(productRepository.save(product));
    }
}
