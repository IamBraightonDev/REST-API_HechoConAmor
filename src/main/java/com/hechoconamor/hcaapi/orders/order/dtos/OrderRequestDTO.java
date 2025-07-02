package com.hechoconamor.hcaapi.orders.order.dtos;

import com.hechoconamor.hcaapi.orders.order_detail.dtos.OrderDetailRequestDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDTO {

    @NotNull(message = "El ID del cliente es obligatorio")
    private Integer clientId;

    @NotNull(message = "Debe haber al menos un producto en el pedido")
    @Size(min = 1, message = "El pedido debe tener al menos un detalle")
    private List<OrderDetailRequestDTO> detalles;
}