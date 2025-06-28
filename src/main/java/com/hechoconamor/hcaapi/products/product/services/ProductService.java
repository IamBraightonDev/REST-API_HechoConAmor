package com.hechoconamor.hcaapi.products.product.services;

import com.hechoconamor.hcaapi.products.product.dtos.ProductRequestDTO;
import com.hechoconamor.hcaapi.products.product.dtos.ProductResponseDTO;

import java.util.List;

public interface ProductService {

    ProductResponseDTO registerProduct(ProductRequestDTO dto);

    List<ProductResponseDTO> findAll();

    List<ProductResponseDTO> findAllByCategory(String category);

    ProductResponseDTO findById(Integer id);

    ProductResponseDTO findByName(String name);

    ProductResponseDTO updateProduct(Integer id, ProductRequestDTO requestDTO);

    void delete(Integer id);

}
