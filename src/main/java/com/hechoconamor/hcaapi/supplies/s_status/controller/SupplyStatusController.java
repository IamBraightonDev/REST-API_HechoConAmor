package com.hechoconamor.hcaapi.supplies.s_status.controller;

import com.hechoconamor.hcaapi.supplies.s_status.dtos.SupplyStatusRequestDTO;
import com.hechoconamor.hcaapi.supplies.s_status.dtos.SupplyStatusResponseDTO;
import com.hechoconamor.hcaapi.supplies.s_status.service.SupplyStatusService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/supplies/status")
public class SupplyStatusController {

    private final SupplyStatusService statusService;

    public SupplyStatusController(SupplyStatusService statusService) {
        this.statusService = statusService;
    }


    // ************************ Create - CRUD ************************ //
    @PostMapping
    public ResponseEntity<SupplyStatusResponseDTO> registerStatus(@RequestBody SupplyStatusRequestDTO requestDTO) {
        SupplyStatusResponseDTO newStatus = statusService.registerStatus(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newStatus); // 201 Created
    }


    // ************************ Read - CRUD ************************ //
    @GetMapping
    public ResponseEntity<List<SupplyStatusResponseDTO>> findAll() {
        return ResponseEntity.ok(statusService.findAll()); // 200 OK
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<SupplyStatusResponseDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(statusService.findById(id)); // 200 OK
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<SupplyStatusResponseDTO> findByName(@PathVariable String name) {
        return ResponseEntity.ok(statusService.findByNameIgnoreCase(name)); // 200 OK
    }


    // ************************ Update - CRUD ************************ //
    @PutMapping("/{id}")
    public ResponseEntity<SupplyStatusResponseDTO> updateStatus(@PathVariable Integer id,
                                                                @Valid @RequestBody
                                                                SupplyStatusRequestDTO requestDTO) {
        SupplyStatusResponseDTO updatedStatus = statusService.updateStatus(id, requestDTO);
        return ResponseEntity.ok(updatedStatus); // 200 OK
    }


    // ************************ Delete - CRUD ************************ //
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatus(@PathVariable Integer id) {
        statusService.deleteStatus(id);
        return ResponseEntity.noContent().build(); // 204 Not Found
    }

}
