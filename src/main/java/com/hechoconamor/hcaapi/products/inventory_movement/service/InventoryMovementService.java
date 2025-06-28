package com.hechoconamor.hcaapi.products.inventory_movement.service;

import com.hechoconamor.hcaapi.products.inventory_movement.dtos.InventoryMovementRequestDTO;
import com.hechoconamor.hcaapi.products.inventory_movement.dtos.InventoryMovementResponseDTO;

import java.util.List;

public interface InventoryMovementService {

    InventoryMovementResponseDTO registerMovement(InventoryMovementRequestDTO requestDTO);

    List<InventoryMovementResponseDTO> findAll();

    InventoryMovementResponseDTO findById(Integer id);

    void delete(Integer id);

}
