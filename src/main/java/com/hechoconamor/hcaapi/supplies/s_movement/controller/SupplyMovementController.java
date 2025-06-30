package com.hechoconamor.hcaapi.supplies.s_movement.controller;

import com.hechoconamor.hcaapi.supplies.s_movement.dtos.SupplyMovementRequestDTO;
import com.hechoconamor.hcaapi.supplies.s_movement.dtos.SupplyMovementResponseDTO;
import com.hechoconamor.hcaapi.supplies.s_movement.services.SupplyMovementService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/supplies/movements")
public class SupplyMovementController {

    private final SupplyMovementService movementService;

    public SupplyMovementController(SupplyMovementService movementService) {
        this.movementService = movementService;
    }

    // ************************ Create - CRUD ************************ //
    @PostMapping
    public ResponseEntity<SupplyMovementResponseDTO> registerMovement(@Valid @RequestBody
                                                                  SupplyMovementRequestDTO requestDTO) {
        SupplyMovementResponseDTO response = movementService.registerMovement(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response); // 201 Created
    }

    // ************************ Read - CRUD ************************ //
    @GetMapping
    public ResponseEntity<List<SupplyMovementResponseDTO>> findAllMovements() {
        List<SupplyMovementResponseDTO> movements = movementService.findAll();
        return ResponseEntity.ok(movements); // 200 OK
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<SupplyMovementResponseDTO> findById(@PathVariable Integer id) {
        SupplyMovementResponseDTO movement = movementService.findById(id);
        return ResponseEntity.ok(movement); // 200 OK
    }

    // ************************ Delete - CRUD ************************ //
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovement(@PathVariable Integer id) {
        movementService.delete(id);
        return ResponseEntity.noContent().build(); // 204 Not Found
    }

}
