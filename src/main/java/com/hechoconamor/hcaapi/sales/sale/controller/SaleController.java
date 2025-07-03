package com.hechoconamor.hcaapi.sales.sale.controller;

import com.hechoconamor.hcaapi.sales.sale.dtos.SaleRequestDTO;
import com.hechoconamor.hcaapi.sales.sale.dtos.SaleResponseDTO;
import com.hechoconamor.hcaapi.sales.sale.services.SaleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    public ResponseEntity<SaleResponseDTO> create(@Valid @RequestBody
                                                      SaleRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(saleService.create(dto));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<SaleResponseDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(saleService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<SaleResponseDTO>> getAll() {
        return ResponseEntity.ok(saleService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        saleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}