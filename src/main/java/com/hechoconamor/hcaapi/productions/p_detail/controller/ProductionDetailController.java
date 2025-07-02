package com.hechoconamor.hcaapi.productions.p_detail.controller;

import com.hechoconamor.hcaapi.productions.p_detail.dtos.ProductionDetailResponseDTO;
import com.hechoconamor.hcaapi.productions.p_detail.services.ProductionDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productions/details")
public class ProductionDetailController {

    private final ProductionDetailService productionDetailService;

    public ProductionDetailController(ProductionDetailService productionDetailService) {
        this.productionDetailService = productionDetailService;
    }


    // ************************ Read - CRUD ************************ //
    @GetMapping("/production/{productionId}")
    public ResponseEntity<List<ProductionDetailResponseDTO>> findByProductionId(@PathVariable Integer productionId) {
        List<ProductionDetailResponseDTO> details = productionDetailService.findByProductionId(productionId);
        return ResponseEntity.ok(details);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ProductionDetailResponseDTO> findById(@PathVariable Integer id) {
        ProductionDetailResponseDTO detail = productionDetailService.findById(id);
        return ResponseEntity.ok(detail);
    }

}
