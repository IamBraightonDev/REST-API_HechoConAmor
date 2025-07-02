package com.hechoconamor.hcaapi.productions.production.services;

import com.hechoconamor.hcaapi.productions.production.dtos.ProductionRequestDTO;
import com.hechoconamor.hcaapi.productions.production.dtos.ProductionResponseDTO;

import java.util.List;

public interface ProductionService {

    ProductionResponseDTO register(ProductionRequestDTO requestDTO);

    List<ProductionResponseDTO> findAll();

    ProductionResponseDTO findById(Integer id);

}
