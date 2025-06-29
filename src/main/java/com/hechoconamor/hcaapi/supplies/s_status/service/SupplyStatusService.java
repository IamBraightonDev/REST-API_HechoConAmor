package com.hechoconamor.hcaapi.supplies.s_status.service;

import com.hechoconamor.hcaapi.supplies.s_status.dtos.SupplyStatusRequestDTO;
import com.hechoconamor.hcaapi.supplies.s_status.dtos.SupplyStatusResponseDTO;

import java.util.List;

public interface SupplyStatusService {

    SupplyStatusResponseDTO registerStatus(SupplyStatusRequestDTO requestDTO);

    List<SupplyStatusResponseDTO> findAll();

    SupplyStatusResponseDTO findById(Integer id);

    SupplyStatusResponseDTO findByNameIgnoreCase(String name);

    SupplyStatusResponseDTO updateStatus(Integer id, SupplyStatusRequestDTO requestDTO);

    void deleteStatus(Integer id);

}
