package com.hechoconamor.hcaapi.products.inventory_movement.mapper;

import com.hechoconamor.hcaapi.products.inventory_movement.dtos.InventoryMovementResponseDTO;
import com.hechoconamor.hcaapi.products.inventory_movement.entity.InventoryMovement;
import org.springframework.stereotype.Component;

@Component
public class InventoryMovementMapper {

    public InventoryMovementResponseDTO toResponseDTO(InventoryMovement movement) {
        return InventoryMovementResponseDTO.builder()
                .id(movement.getId())
                .quantity(movement.getQuantity())
                .type(movement.getType())
                .reason(movement.getReason())
                .productId(movement.getProduct().getId())
                .productName(movement.getProduct().getName())
                .createdAt(movement.getDate())
                .build();
    }
}
