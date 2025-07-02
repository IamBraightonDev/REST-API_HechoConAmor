package com.hechoconamor.hcaapi.supplies.s_restock.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplyRestockRequestDTO {

    @NotNull(message = "El ID del insumo es obligatorio.")
    private Integer supplyId;

    @NotNull(message = "La cantidad es obligatoria.")
    @Min(value = 1, message = "La cantidad debe ser mayor a cero.")
    private Integer quantity;

    private String reason;

}
