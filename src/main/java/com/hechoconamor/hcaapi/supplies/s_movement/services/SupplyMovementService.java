package com.hechoconamor.hcaapi.supplies.s_movement.services;

import com.hechoconamor.hcaapi.supplies.s_movement.dtos.SupplyMovementRequestDTO;
import com.hechoconamor.hcaapi.supplies.s_movement.dtos.SupplyMovementResponseDTO;

import java.util.List;

public interface SupplyMovementService {

    SupplyMovementResponseDTO registerMovement(SupplyMovementRequestDTO requestDTO);

    List<SupplyMovementResponseDTO> findAll();

    SupplyMovementResponseDTO findById(Integer id);

    void delete(Integer id);

}
