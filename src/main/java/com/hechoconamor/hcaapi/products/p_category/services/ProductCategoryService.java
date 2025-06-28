package com.hechoconamor.hcaapi.products.p_category.services;

import com.hechoconamor.hcaapi.products.p_category.dtos.ProductCategoryRequestDTO;
import com.hechoconamor.hcaapi.products.p_category.dtos.ProductCategoryResponseDTO;

import java.util.List;

public interface ProductCategoryService {

    ProductCategoryResponseDTO registerCategory(ProductCategoryRequestDTO productCategoryRequestDTO);

    List<ProductCategoryResponseDTO> findAll();

    ProductCategoryResponseDTO findById(Integer id);

    ProductCategoryResponseDTO findByNameIgnoreCase(String name);

    ProductCategoryResponseDTO updatedCategory(Integer id, ProductCategoryRequestDTO productCategoryRequestDTO);

    void deleteCategory(Integer id);

}
