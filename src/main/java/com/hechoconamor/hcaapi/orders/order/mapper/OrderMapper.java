package com.hechoconamor.hcaapi.orders.order.mapper;

import com.hechoconamor.hcaapi.orders.client.entity.Client;
import com.hechoconamor.hcaapi.orders.order.dtos.OrderRequestDTO;
import com.hechoconamor.hcaapi.orders.order.dtos.OrderResponseDTO;
import com.hechoconamor.hcaapi.orders.order.entity.Order;
import com.hechoconamor.hcaapi.orders.order.enums.OrderStatus;
import com.hechoconamor.hcaapi.orders.order_detail.dtos.OrderDetailResponseDTO;
import com.hechoconamor.hcaapi.orders.order_detail.entity.OrderDetail;
import com.hechoconamor.hcaapi.orders.order_detail.mapper.OrderDetailMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class OrderMapper {

    private final OrderDetailMapper detailMapper;

    public OrderMapper(OrderDetailMapper detailMapper) {
        this.detailMapper = detailMapper;
    }

    public Order toEntity(OrderRequestDTO dto, Client client, List<OrderDetail> detalles) {
        return Order.builder()
                .fecha(dto.getFecha())
                .client(client)
                .estado(OrderStatus.PENDIENTE)
                .detalles(detalles)
                .build();
    }

    public OrderResponseDTO toResponseDTO(Order order) {
        List<OrderDetailResponseDTO> detallesDTO = order.getDetalles().stream()
                .map(detailMapper::toResponseDTO)
                .toList();

        return OrderResponseDTO.builder()
                .id(order.getId())
                .fecha(order.getFecha())
                .clientId(order.getClient().getId())
                .clientNombre(order.getClient().getNombre())
                .clientEmail(order.getClient().getEmail())
                .clientTelefono(order.getClient().getTelefono())
                .clientDireccion(order.getClient().getDireccion())
                .estado(order.getEstado())
                .detalles(detallesDTO)
                .build();
    }
}
