package com.hechoconamor.hcaapi.inventory.dtos;

import com.hechoconamor.hcaapi.inventory.enums.MovementType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryMovementResponseDTO {

    private Integer id;
    private Integer quantity;
    private MovementType type;
    private String reason;
    private String productName;
    private Integer productId;
    private LocalDateTime createdAt;

}
