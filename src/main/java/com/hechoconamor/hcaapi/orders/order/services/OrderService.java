package com.hechoconamor.hcaapi.orders.order.services;

import com.hechoconamor.hcaapi.orders.order.dtos.OrderRequestDTO;
import com.hechoconamor.hcaapi.orders.order.dtos.OrderResponseDTO;
import com.hechoconamor.hcaapi.orders.order.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    OrderResponseDTO create(OrderRequestDTO dto);

    OrderResponseDTO getById(Integer id);

    List<OrderResponseDTO> getAll();

    OrderResponseDTO updateStatus(Integer id, OrderStatus newStatus);

    OrderResponseDTO updateDate(Integer id, LocalDateTime newDate);

    void delete(Integer id);
}