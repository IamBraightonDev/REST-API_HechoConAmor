package com.hechoconamor.hcaapi.sales.sale.services;

import com.hechoconamor.hcaapi.sales.sale.dtos.SaleRequestDTO;
import com.hechoconamor.hcaapi.sales.sale.dtos.SaleResponseDTO;

import java.util.List;

public interface SaleService {

    SaleResponseDTO create(SaleRequestDTO dto);

    SaleResponseDTO getById(Integer id);

    List<SaleResponseDTO> getAll();

    void delete(Integer id);
}
