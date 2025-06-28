package com.hechoconamor.hcaapi.inventory.controller;

import com.hechoconamor.hcaapi.inventory.dtos.InventoryMovementRequestDTO;
import com.hechoconamor.hcaapi.inventory.dtos.InventoryMovementResponseDTO;
import com.hechoconamor.hcaapi.inventory.service.InventoryMovementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory/movements")
@RequiredArgsConstructor
public class InventoryMovementController {

    private final InventoryMovementService movementService;

    // 🟢 Crear un nuevo movimiento
    @PostMapping
    public ResponseEntity<InventoryMovementResponseDTO> registerMovement(@Valid @RequestBody InventoryMovementRequestDTO requestDTO) {
        InventoryMovementResponseDTO responseDTO = movementService.registerMovement(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    // 🔵 Obtener todos los movimientos
    @GetMapping
    public ResponseEntity<List<InventoryMovementResponseDTO>> findAllMovements() {
        return ResponseEntity.ok(movementService.findAll()); // 200 OK
    }

    // 🟡 Obtener movimiento por ID
    @GetMapping("/id/{id}")
    public ResponseEntity<InventoryMovementResponseDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(movementService.findById(id));
    }

    // 🔴 Eliminar movimiento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovement(@PathVariable Integer id) {
        movementService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
