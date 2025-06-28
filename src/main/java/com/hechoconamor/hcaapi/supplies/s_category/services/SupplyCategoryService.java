package com.hechoconamor.hcaapi.supplies.s_category.services;

import com.hechoconamor.hcaapi.supplies.s_category.dtos.SupplyCategoryRequestDTO;
import com.hechoconamor.hcaapi.supplies.s_category.dtos.SupplyCategoryResponseDTO;

import java.util.List;

public interface SupplyCategoryService {

    SupplyCategoryResponseDTO registerCategory(SupplyCategoryRequestDTO requestDTO);

    List<SupplyCategoryResponseDTO> findAll();

    SupplyCategoryResponseDTO findById(Integer id);

    SupplyCategoryResponseDTO findByName(String name);

    SupplyCategoryResponseDTO updateCategory(Integer id, SupplyCategoryRequestDTO requestDTO);

    void deleteCategory(Integer id);

}
