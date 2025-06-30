package com.hechoconamor.hcaapi.supplies.supply.controller;

import com.hechoconamor.hcaapi.supplies.supply.dtos.SupplyRequestDTO;
import com.hechoconamor.hcaapi.supplies.supply.dtos.SupplyResponseDTO;
import com.hechoconamor.hcaapi.supplies.supply.services.SupplyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/supplies")
public class SupplyController {

    private final SupplyService service;

    public SupplyController(SupplyService service) {
        this.service = service;
    }


    // ************************ Create - CRUD ************************ //
    @PostMapping
    public ResponseEntity<SupplyResponseDTO> registerSupply(@Valid @RequestBody SupplyRequestDTO dto) {
        SupplyResponseDTO newSupply = service.registerSupply(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newSupply); // 201 Created
    }


    // ************************ Read - CRUD ************************ //
    @GetMapping
    public ResponseEntity<List<SupplyResponseDTO>> findAllSupplies() {
        List<SupplyResponseDTO> supplies = service.findAll();
        return ResponseEntity.ok(supplies); // 200 OK
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<SupplyResponseDTO> findById(@PathVariable Integer id) {
        SupplyResponseDTO supply = service.findById(id);
        return ResponseEntity.ok(supply);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<SupplyResponseDTO> findByName(@RequestParam String name) {
        SupplyResponseDTO supply = service.findByNameIgnoreCase(name);
        return ResponseEntity.ok(supply);
    }


    // ************************ Update - CRUD ************************ //
    @PutMapping("/{id}")
    public ResponseEntity<SupplyResponseDTO> updateSupply(@PathVariable Integer id,
                                                    @Valid @RequestBody SupplyRequestDTO dto) {
        SupplyResponseDTO updated = service.updateSupply(id, dto);
        return ResponseEntity.ok(updated);
    }


    // ************************ Delete - CRUD ************************ //
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteSupply(id);
        return ResponseEntity.noContent().build();
    }

}
