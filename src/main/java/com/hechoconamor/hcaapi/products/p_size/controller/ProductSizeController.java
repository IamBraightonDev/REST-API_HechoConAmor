package com.hechoconamor.hcaapi.products.p_size.controller;

import com.hechoconamor.hcaapi.products.p_size.dtos.ProductSizeRequestDTO;
import com.hechoconamor.hcaapi.products.p_size.dtos.ProductSizeResponseDTO;
import com.hechoconamor.hcaapi.products.p_size.services.ProductSizeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product/size")
public class ProductSizeController {

    private final ProductSizeService productSizeService;

    public ProductSizeController(ProductSizeService productSizeService) {
        this.productSizeService = productSizeService;
    }


    // ************************ Create - CRUD ************************ //
    @PostMapping
    public ResponseEntity<ProductSizeResponseDTO> registerSize(@Valid @RequestBody
                                                               ProductSizeRequestDTO productSizeRequestDTO) {
        ProductSizeResponseDTO newProductSize = productSizeService.registerSize(productSizeRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProductSize); // 201 Created
    }


    // ************************ Read - CRUD ************************ //
    @GetMapping
    public ResponseEntity<List<ProductSizeResponseDTO>> findAllSizes() {
        return ResponseEntity.ok(productSizeService.findAll()); // 200 OK
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ProductSizeResponseDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(productSizeService.findById(id)); // 200 OK
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ProductSizeResponseDTO> findByName(@PathVariable String name) {
        return ResponseEntity.ok(productSizeService.findByNameIgnoreCase(name)); // 200 OK
    }


    // ************************ Update - CRUD ************************ //
    @PutMapping("/{id}")
    public ResponseEntity<ProductSizeResponseDTO> updateSize(@PathVariable Integer id,
                                                             @Valid @RequestBody
                                                             ProductSizeRequestDTO productSizeRequestDTO) {
        ProductSizeResponseDTO updatedSize = productSizeService.updateSize(id, productSizeRequestDTO);
        return ResponseEntity.ok(updatedSize); // 200 OK
    }


    // ************************ Delete - CRUD ************************ //
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSize(@PathVariable Integer id) {
        productSizeService.deleteSize(id);
        return ResponseEntity.noContent().build(); // 204 Not Found
    }

}
