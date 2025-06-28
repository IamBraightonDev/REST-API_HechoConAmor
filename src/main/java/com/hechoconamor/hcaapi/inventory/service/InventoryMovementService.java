package com.hechoconamor.hcaapi.inventory.service;

import com.hechoconamor.hcaapi.inventory.dtos.InventoryMovementRequestDTO;
import com.hechoconamor.hcaapi.inventory.dtos.InventoryMovementResponseDTO;

import java.util.List;

public interface InventoryMovementService {

    InventoryMovementResponseDTO registerMovement(InventoryMovementRequestDTO requestDTO);

    List<InventoryMovementResponseDTO> findAll();

    InventoryMovementResponseDTO findById(Integer id);

    void delete(Integer id);

}
