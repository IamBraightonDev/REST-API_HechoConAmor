package com.hechoconamor.hcaapi.sales.sale.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleRequestDTO {

    @NotNull(message = "El ID del pedido es obligatorio")
    private Integer orderId;

    private String metodoPago;
}