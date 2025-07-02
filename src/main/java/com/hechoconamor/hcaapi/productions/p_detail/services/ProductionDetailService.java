package com.hechoconamor.hcaapi.productions.p_detail.services;

import com.hechoconamor.hcaapi.productions.p_detail.dtos.ProductionDetailResponseDTO;

import java.util.List;

public interface ProductionDetailService {

    List<ProductionDetailResponseDTO> findByProductionId(Integer productionId);

    ProductionDetailResponseDTO findById(Integer id);

}
