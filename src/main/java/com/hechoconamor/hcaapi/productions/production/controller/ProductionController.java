package com.hechoconamor.hcaapi.productions.production.controller;

import com.hechoconamor.hcaapi.productions.production.dtos.ProductionRequestDTO;
import com.hechoconamor.hcaapi.productions.production.dtos.ProductionResponseDTO;
import com.hechoconamor.hcaapi.productions.production.services.ProductionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productions")
public class ProductionController {

    private final ProductionService productionService;

    public ProductionController(ProductionService productionService) {
        this.productionService = productionService;
    }


    // ************************ Create - CRUD ************************ //
    @PostMapping
    public ResponseEntity<ProductionResponseDTO> register(@Valid @RequestBody
                                                              ProductionRequestDTO requestDTO) {
        ProductionResponseDTO response = productionService.register(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response); // 201 Created
    }


    // ************************ Read - CRUD ************************ //
    @GetMapping
    public ResponseEntity<List<ProductionResponseDTO>> findAll() {
        List<ProductionResponseDTO> productions = productionService.findAll();
        return ResponseEntity.ok(productions); // 200 OK
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ProductionResponseDTO> findById(@PathVariable Integer id) {
        ProductionResponseDTO response = productionService.findById(id);
        return ResponseEntity.ok(response); // 200 OK
    }

}
