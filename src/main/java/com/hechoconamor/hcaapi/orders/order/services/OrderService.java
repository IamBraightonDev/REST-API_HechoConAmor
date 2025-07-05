package com.hechoconamor.hcaapi.orders.order.services;

import com.hechoconamor.hcaapi.orders.order.dtos.OrderRequestDTO;
import com.hechoconamor.hcaapi.orders.order.dtos.OrderResponseDTO;
import com.hechoconamor.hcaapi.orders.order.enums.OrderStatus;

import java.util.List;

public interface OrderService {

    OrderResponseDTO create(OrderRequestDTO dto);

    OrderResponseDTO getById(Integer id);

    List<OrderResponseDTO> getAll();

    OrderResponseDTO updateStatus(Integer id, OrderStatus newStatus);

    void delete(Integer id);
}