package com.hechoconamor.hcaapi._product.p_status.services;

import com.hechoconamor.hcaapi._product.p_status.dtos.ProductStatusRequestDTO;
import com.hechoconamor.hcaapi._product.p_status.dtos.ProductStatusResponseDTO;

import java.util.List;

public interface ProductStatusService {

    ProductStatusResponseDTO registerStatus(ProductStatusRequestDTO productStatusRequestDTO);

    List<ProductStatusResponseDTO> findAll();

    ProductStatusResponseDTO findById(Integer id);

    ProductStatusResponseDTO findByNameIgnoreCase(String name);

    ProductStatusResponseDTO updateStatus(Integer id, ProductStatusRequestDTO productStatusRequestDTO);

    void deleteStatus(Integer id);

}
