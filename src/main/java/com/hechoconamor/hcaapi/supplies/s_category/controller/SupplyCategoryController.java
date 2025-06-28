package com.hechoconamor.hcaapi.supplies.s_category.controller;

import com.hechoconamor.hcaapi.supplies.s_category.dtos.SupplyCategoryRequestDTO;
import com.hechoconamor.hcaapi.supplies.s_category.dtos.SupplyCategoryResponseDTO;
import com.hechoconamor.hcaapi.supplies.s_category.services.SupplyCategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/supply/categories")
public class SupplyCategoryController {

    private final SupplyCategoryService categoryService;

    public SupplyCategoryController(SupplyCategoryService categoryService) {
        this.categoryService = categoryService;
    }


    // ************************ Create - CRUD ************************ //
    @PostMapping
    public ResponseEntity<SupplyCategoryResponseDTO> registerCategory(@Valid @RequestBody SupplyCategoryRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.registerCategory(requestDTO)); // 201 Created
    }


    // ************************ Read - CRUD ************************ //
    @GetMapping
    public ResponseEntity<List<SupplyCategoryResponseDTO>> findAll() {
        return ResponseEntity.ok(categoryService.findAll()); // 200 OK
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<SupplyCategoryResponseDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(categoryService.findById(id)); // 200 OK
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<SupplyCategoryResponseDTO> findByName(@PathVariable String name) {
        return ResponseEntity.ok(categoryService.findByName(name)); // 200 OK
    }


    // ************************ Update - CRUD ************************ //
    @PutMapping("/{id}")
    public ResponseEntity<SupplyCategoryResponseDTO> updateCategory(@PathVariable Integer id,
                                                            @Valid @RequestBody SupplyCategoryRequestDTO requestDTO) {
        return ResponseEntity.ok(categoryService.updateCategory(id, requestDTO)); // 200 OK
    }


    // ************************ Delete - CRUD ************************ //
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build(); // 204 Not Found
    }

}
