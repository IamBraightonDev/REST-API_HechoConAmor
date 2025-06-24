package com.hechoconamor.hcaapi._product.category.services;

import com.hechoconamor.hcaapi._product.category.dtos.ProductCategoryRequestDTO;
import com.hechoconamor.hcaapi._product.category.dtos.ProductCategoryResponseDTO;

import java.util.List;

public interface ProductCategoryService {

    ProductCategoryResponseDTO registerCategory(ProductCategoryRequestDTO productCategoryRequestDTO);

    List<ProductCategoryResponseDTO> findAll();

    ProductCategoryResponseDTO findById(Integer id);

    ProductCategoryResponseDTO findByNameIgnoreCase(String name);

    ProductCategoryResponseDTO updatedCategory(Integer id, ProductCategoryRequestDTO productCategoryRequestDTO);

    void deleteCategory(Integer id);

}
