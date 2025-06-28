package com.hechoconamor.hcaapi.products.p_product.mapper;

import com.hechoconamor.hcaapi.products.p_product.dtos.ProductResponseDTO;
import com.hechoconamor.hcaapi.products.p_product.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponseDTO toResponseDTO(Product product, Integer stock) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStock(stock);

        dto.setCategory(product.getCategory().getName());
        dto.setColor(product.getColor().getName());
        dto.setMaterial(product.getMaterial().getName());
        dto.setSize(product.getSize().getName());
        dto.setStatus(product.getStatus().getName());

        return dto;
    }

}
