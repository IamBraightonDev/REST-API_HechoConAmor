package com.hechoconamor.hcaapi.supplies.s_restock.controller;

import com.hechoconamor.hcaapi.supplies.s_restock.dtos.SupplyRestockRequestDTO;
import com.hechoconamor.hcaapi.supplies.s_restock.dtos.SupplyRestockResponseDTO;
import com.hechoconamor.hcaapi.supplies.s_restock.services.SupplyRestockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/supplies/restocks")
@RequiredArgsConstructor
public class SupplyRestockController {

    private final SupplyRestockService restockService;

    @PostMapping
    public ResponseEntity<SupplyRestockResponseDTO> register(@Valid @RequestBody SupplyRestockRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(restockService.register(dto));
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<SupplyRestockResponseDTO> approve(@PathVariable Integer id) {
        return ResponseEntity.ok(restockService.approve(id));
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<SupplyRestockResponseDTO> reject(
            @PathVariable Integer id,
            @RequestParam String reason
    ) {
        return ResponseEntity.ok(restockService.reject(id, reason));
    }

    @GetMapping
    public ResponseEntity<List<SupplyRestockResponseDTO>> findAll() {
        return ResponseEntity.ok(restockService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<SupplyRestockResponseDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(restockService.findById(id));
    }

}
