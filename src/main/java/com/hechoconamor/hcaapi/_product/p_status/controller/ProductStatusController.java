package com.hechoconamor.hcaapi._product.p_status.controller;

import com.hechoconamor.hcaapi._product.p_status.dtos.ProductStatusRequestDTO;
import com.hechoconamor.hcaapi._product.p_status.dtos.ProductStatusResponseDTO;
import com.hechoconamor.hcaapi._product.p_status.services.ProductStatusService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product/status")
public class ProductStatusController {

    private final ProductStatusService productStatusService;

    public ProductStatusController(ProductStatusService productStatusService) {
        this.productStatusService = productStatusService;
    }


    // ************************ Create - CRUD ************************ //
    @PostMapping
    public ResponseEntity<ProductStatusResponseDTO> registerStatus(@Valid @RequestBody
                                                                   ProductStatusRequestDTO productStatusRequestDTO) {
        ProductStatusResponseDTO newStatus = productStatusService.registerStatus(productStatusRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newStatus); // 201 Created
    }


    // ************************ Read - CRUD ************************ //
    @GetMapping
    public ResponseEntity<List<ProductStatusResponseDTO>> findAllStatus() {
        return ResponseEntity.ok(productStatusService.findAll()); // 200 OK
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ProductStatusResponseDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(productStatusService.findById(id)); // 200 OK
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ProductStatusResponseDTO> findByName(@PathVariable String name) {
        return ResponseEntity.ok(productStatusService.findByNameIgnoreCase(name)); // 200 OK
    }


    // ************************ Update - CRUD ************************ //
    @PutMapping("/{id}")
    public ResponseEntity<ProductStatusResponseDTO> updateStatus(@PathVariable Integer id,
                                                                 @Valid @RequestBody
                                                                 ProductStatusRequestDTO productStatusRequestDTO) {
        ProductStatusResponseDTO updatedStatus = productStatusService.updateStatus(id, productStatusRequestDTO);
        return ResponseEntity.ok(updatedStatus); // 200 OK
    }


    // ************************ Delete - CRUD ************************ //
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatus(@PathVariable Integer id) {
        productStatusService.deleteStatus(id);
        return ResponseEntity.noContent().build(); // 204 Not Found
    }

}
