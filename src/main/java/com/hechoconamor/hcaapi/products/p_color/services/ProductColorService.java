package com.hechoconamor.hcaapi.products.p_color.services;

import com.hechoconamor.hcaapi.products.p_color.dtos.ProductColorRequestDTO;
import com.hechoconamor.hcaapi.products.p_color.dtos.ProductColorResponseDTO;

import java.util.List;

public interface ProductColorService {

    ProductColorResponseDTO registerColor(ProductColorRequestDTO productColorRequestDTO);

    List<ProductColorResponseDTO> findAll();

    ProductColorResponseDTO findById(Integer id);

    ProductColorResponseDTO findByName(String name);

    ProductColorResponseDTO updateColor(Integer id, ProductColorRequestDTO productColorRequestDTO);

    void deleteColor(Integer id);

}
