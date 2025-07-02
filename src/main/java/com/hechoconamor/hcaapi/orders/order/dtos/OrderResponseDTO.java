package com.hechoconamor.hcaapi.orders.order.dtos;

import com.hechoconamor.hcaapi.orders.order.enums.OrderStatus;
import com.hechoconamor.hcaapi.orders.order_detail.dtos.OrderDetailResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDTO {

    private Integer id;
    private LocalDateTime fecha;

    private Integer clientId;
    private String clientNombre;
    private String clientEmail;

    private OrderStatus estado;

    private List<OrderDetailResponseDTO> detalles;
}