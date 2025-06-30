package com.hechoconamor.hcaapi.supplies.s_movement.mapper;

import com.hechoconamor.hcaapi.supplies.s_movement.dtos.SupplyMovementResponseDTO;
import com.hechoconamor.hcaapi.supplies.s_movement.entity.SupplyMovement;
import org.springframework.stereotype.Component;

@Component
public class SupplyMovementMapper {

    public SupplyMovementResponseDTO toResponseDTO(SupplyMovement movement) {
        return SupplyMovementResponseDTO.builder()
                .id(movement.getId())
                .quantity(movement.getQuantity())
                .reason(movement.getReason())
                .createdAt(movement.getDate())
                .movementType(movement.getMovementType())
                .supplyId(movement.getSupply().getId())
                .supplyName(movement.getSupply().getName())
                .build();
    }
}