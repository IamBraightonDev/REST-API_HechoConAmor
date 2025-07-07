package com.hechoconamor.hcaapi.orders.order.services.impl;

import com.hechoconamor.hcaapi.common.MovementType;
import com.hechoconamor.hcaapi.orders.client.entity.Client;
import com.hechoconamor.hcaapi.orders.order.dtos.OrderRequestDTO;
import com.hechoconamor.hcaapi.orders.order.dtos.OrderResponseDTO;
import com.hechoconamor.hcaapi.orders.order.entity.Order;
import com.hechoconamor.hcaapi.orders.order.enums.OrderStatus;
import com.hechoconamor.hcaapi.orders.order.mapper.OrderMapper;
import com.hechoconamor.hcaapi.orders.order.repository.OrderRepository;
import com.hechoconamor.hcaapi.orders.order.services.OrderService;
import com.hechoconamor.hcaapi.orders.order.validator.OrderValidator;
import com.hechoconamor.hcaapi.orders.order_detail.entity.OrderDetail;
import com.hechoconamor.hcaapi.orders.order_detail.services.OrderDetailService;
import com.hechoconamor.hcaapi.products.inventory_movement.entity.InventoryMovement;
import com.hechoconamor.hcaapi.products.inventory_movement.repository.InventoryMovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderValidator orderValidator;
    private final OrderMapper orderMapper;

    private final OrderDetailService orderDetailService;
    private final InventoryMovementRepository movementRepository;

    @Override
    public OrderResponseDTO create(OrderRequestDTO dto) {
        // 1. Validar cliente
        Client client = orderValidator.validateClientExists(dto.getClientId());

        // 2. Validar y construir detalles
        List<OrderDetail> details = orderDetailService.buildValidatedDetails(dto.getDetalles());

        // 3. Construir entidad Order
        Order order = orderMapper.toEntity(dto, client, details);

        // 4. Asignar order a cada detalle
        details.forEach(d -> d.setOrder(order));

        // 5. Guardar el pedido
        Order savedOrder = orderRepository.save(order);

        // 6. Generar movimientos de salida por producto
        details.forEach(detalle -> {
            InventoryMovement movement = InventoryMovement.builder()
                    .product(detalle.getProduct())
                    .quantity(detalle.getCantidad())
                    .type(MovementType.SALIDA)
                    .reason("Pedido ID: " + savedOrder.getId())
                    .date(order.getFecha())
                    .build();
            movementRepository.save(movement);
        });

        // 7. Devolver respuesta
        return orderMapper.toResponseDTO(savedOrder);
    }

    @Override
    public OrderResponseDTO getById(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pedido no encontrado"));
        return orderMapper.toResponseDTO(order);
    }

    @Override
    public List<OrderResponseDTO> getAll() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toResponseDTO)
                .toList();
    }

    @Override
    public OrderResponseDTO updateStatus(Integer id, OrderStatus newStatus) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pedido no encontrado"));
        order.setEstado(newStatus);
        Order updatedOrder = orderRepository.save(order);
        return orderMapper.toResponseDTO(updatedOrder);
    }

    @Override
    public OrderResponseDTO updateDate(Integer id, LocalDateTime newDate) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pedido no encontrado"));
        order.setFecha(newDate);
        Order updatedOrder = orderRepository.save(order);
        return orderMapper.toResponseDTO(updatedOrder);
    }

    @Override
    public void delete(Integer id) {
        if (!orderRepository.existsById(id)) {
            throw new NoSuchElementException("Pedido no encontrado");
        }
        orderRepository.deleteById(id);
    }
}