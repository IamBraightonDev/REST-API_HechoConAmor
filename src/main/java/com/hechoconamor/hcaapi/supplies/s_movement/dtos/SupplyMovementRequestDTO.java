package com.hechoconamor.hcaapi.supplies.s_movement.dtos;

import com.hechoconamor.hcaapi.common.MovementType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplyMovementRequestDTO {

    @NotNull(message = "La cantidad es obligatoria.")
    @Min(value = 1, message = "La cantidad debe ser mayor a cero.")
    private Integer quantity;

    @NotNull(message = "El tipo de movimiento es obligatorio.")
    private MovementType movementType;

    @NotNull(message = "El ID del insumo es obligatorio.")
    private Integer supplyId;

    private String reason; // Opcional

}
