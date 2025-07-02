package com.hechoconamor.hcaapi.orders.order.controller;

import com.hechoconamor.hcaapi.orders.order.dtos.OrderRequestDTO;
import com.hechoconamor.hcaapi.orders.order.dtos.OrderResponseDTO;
import com.hechoconamor.hcaapi.orders.order.enums.OrderStatus;
import com.hechoconamor.hcaapi.orders.order.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> create(@Valid @RequestBody OrderRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.create(dto));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<OrderResponseDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(orderService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAll() {
        return ResponseEntity.ok(orderService.getAll());
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Void> updateEstado(@PathVariable Integer id,
                                             @RequestParam OrderStatus estado) {
        orderService.updateStatus(id, estado);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}