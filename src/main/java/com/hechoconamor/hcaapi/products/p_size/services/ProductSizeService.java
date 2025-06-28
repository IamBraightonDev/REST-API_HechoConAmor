package com.hechoconamor.hcaapi.products.p_size.services;

import com.hechoconamor.hcaapi.products.p_size.dtos.ProductSizeRequestDTO;
import com.hechoconamor.hcaapi.products.p_size.dtos.ProductSizeResponseDTO;

import java.util.List;

public interface ProductSizeService {

    ProductSizeResponseDTO registerSize(ProductSizeRequestDTO productSizeRequestDTO);

    List<ProductSizeResponseDTO> findAll();

    ProductSizeResponseDTO findById(Integer id);

    ProductSizeResponseDTO findByNameIgnoreCase(String name);

    ProductSizeResponseDTO updateSize(Integer id, ProductSizeRequestDTO productSizeRequestDTO);

    void deleteSize(Integer id);

}
