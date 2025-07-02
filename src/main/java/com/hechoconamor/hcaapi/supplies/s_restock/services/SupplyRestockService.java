package com.hechoconamor.hcaapi.supplies.s_restock.services;

import com.hechoconamor.hcaapi.supplies.s_restock.dtos.SupplyRestockRequestDTO;
import com.hechoconamor.hcaapi.supplies.s_restock.dtos.SupplyRestockResponseDTO;

import java.util.List;

public interface SupplyRestockService {

    SupplyRestockResponseDTO register(SupplyRestockRequestDTO dto);

    SupplyRestockResponseDTO approve(Integer id);

    SupplyRestockResponseDTO reject(Integer id, String reason);

    List<SupplyRestockResponseDTO> findAll();

    SupplyRestockResponseDTO findById(Integer id);

}
