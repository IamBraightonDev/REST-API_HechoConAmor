package com.hechoconamor.hcaapi.orders.order.services;

import com.hechoconamor.hcaapi.orders.order.dtos.OrderRequestDTO;
import com.hechoconamor.hcaapi.orders.order.dtos.OrderResponseDTO;
import com.hechoconamor.hcaapi.orders.order.enums.OrderStatus;

import java.util.List;

public interface OrderService {

    OrderResponseDTO create(OrderRequestDTO dto);

    OrderResponseDTO getById(Integer id);

    List<OrderResponseDTO> getAll();

    void updateStatus(Integer id, OrderStatus nuevoEstado);

    void delete(Integer id);
}