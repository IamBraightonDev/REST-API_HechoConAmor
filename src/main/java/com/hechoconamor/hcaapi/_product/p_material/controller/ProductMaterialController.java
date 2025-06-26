package com.hechoconamor.hcaapi._product.p_material.controller;

import com.hechoconamor.hcaapi._product.p_material.dtos.ProductMaterialRequestDTO;
import com.hechoconamor.hcaapi._product.p_material.dtos.ProductMaterialResponseDTO;
import com.hechoconamor.hcaapi._product.p_material.services.ProductMaterialService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product/materials")
public class ProductMaterialController {

    private final ProductMaterialService productMaterialService;

    public ProductMaterialController(ProductMaterialService productMaterialService) {
        this.productMaterialService = productMaterialService;
    }


    // ************************ Create - CRUD ************************ //
    @PostMapping
    public ResponseEntity<ProductMaterialResponseDTO> registerMaterial(@Valid @RequestBody
                                                                       ProductMaterialRequestDTO productMaterialRequestDTO) {
        ProductMaterialResponseDTO newProductMaterial = productMaterialService.registerMaterial(productMaterialRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProductMaterial); // 201 Created
    }


    // ************************ Read - CRUD ************************ //
    @GetMapping
    public ResponseEntity<List<ProductMaterialResponseDTO>> findAllMaterials() {
        return ResponseEntity.ok(productMaterialService.findAll()); // 200 OK
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ProductMaterialResponseDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(productMaterialService.findById(id)); // 200 OK
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ProductMaterialResponseDTO> findByName(@Valid @PathVariable String name) {
        return ResponseEntity.ok(productMaterialService.findByNameIgnoreCase(name)); // 200 OK
    }


    // ************************ Update - CRUD ************************ //
    @PutMapping("/{id}")
    public ResponseEntity<ProductMaterialResponseDTO> updateMaterial(@PathVariable Integer id,
                                                                     @Valid @RequestBody
                                                                     ProductMaterialRequestDTO productMaterialRequestDTO) {
        ProductMaterialResponseDTO updatedProductMaterial = productMaterialService.updateMaterial(id, productMaterialRequestDTO);
        return ResponseEntity.ok(updatedProductMaterial); // 200 OK
    }


    // ************************ Delete - CRUD ************************ //
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable Integer id) {
        productMaterialService.deleteMaterial(id);
        return ResponseEntity.noContent().build(); // 204 Not Found
    }

}
