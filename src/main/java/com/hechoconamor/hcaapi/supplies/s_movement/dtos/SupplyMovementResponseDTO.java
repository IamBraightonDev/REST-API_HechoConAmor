package com.hechoconamor.hcaapi.supplies.s_movement.dtos;

import com.hechoconamor.hcaapi.common.MovementType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupplyMovementResponseDTO {

    private Integer id;

    private Integer quantity;

    private MovementType movementType;

    private Integer supplyId;

    private String supplyName;

    private String reason;

    private LocalDateTime createdAt;

}
