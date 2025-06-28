package com.hechoconamor.hcaapi.products.p_material.services;

import com.hechoconamor.hcaapi.products.p_material.dtos.ProductMaterialRequestDTO;
import com.hechoconamor.hcaapi.products.p_material.dtos.ProductMaterialResponseDTO;

import java.util.List;

public interface ProductMaterialService {

    ProductMaterialResponseDTO registerMaterial(ProductMaterialRequestDTO productMaterialRequestDTO);

    List<ProductMaterialResponseDTO> findAll();

    ProductMaterialResponseDTO findById(Integer id);

    ProductMaterialResponseDTO findByNameIgnoreCase(String name);

    ProductMaterialResponseDTO updateMaterial(Integer id, ProductMaterialRequestDTO productMaterialRequestDTO);

    void deleteMaterial(Integer id);

}
