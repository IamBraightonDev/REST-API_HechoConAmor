package com.hechoconamor.hcaapi.supplies.supply.services;

import com.hechoconamor.hcaapi.supplies.supply.dtos.SupplyRequestDTO;
import com.hechoconamor.hcaapi.supplies.supply.dtos.SupplyResponseDTO;

import java.util.List;

public interface SupplyService {

    SupplyResponseDTO registerSupply(SupplyRequestDTO dto);

    List<SupplyResponseDTO> findAll();

    SupplyResponseDTO findById(Integer id);

    SupplyResponseDTO findByNameIgnoreCase(String name);

    SupplyResponseDTO updateSupply(Integer id, SupplyRequestDTO dto);

    void deleteSupply(Integer id);

}
