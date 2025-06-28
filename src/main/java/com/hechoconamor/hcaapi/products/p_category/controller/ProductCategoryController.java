package com.hechoconamor.hcaapi.products.p_category.controller;

import com.hechoconamor.hcaapi.products.p_category.dtos.ProductCategoryRequestDTO;
import com.hechoconamor.hcaapi.products.p_category.dtos.ProductCategoryResponseDTO;
import com.hechoconamor.hcaapi.products.p_category.services.ProductCategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product/categories")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }


    // ************************ Create - CRUD ************************ //
    @PostMapping
    public ResponseEntity<ProductCategoryResponseDTO> registerCategory(@RequestBody ProductCategoryRequestDTO productCategoryRequestDTO) {
        ProductCategoryResponseDTO newProductCategory = productCategoryService.registerCategory(productCategoryRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProductCategory); // 201 Created
    }


    // ************************ Read - CRUD ************************ //
    @GetMapping
    public ResponseEntity<List<ProductCategoryResponseDTO>> findAllCategories() {
        return ResponseEntity.ok(productCategoryService.findAll()); // 200 OK
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ProductCategoryResponseDTO> findCategoryById(@PathVariable Integer id) {
        return ResponseEntity.ok(productCategoryService.findById(id)); // 200 OK
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ProductCategoryResponseDTO> findCategoryByName(@PathVariable String name) {
        return ResponseEntity.ok(productCategoryService.findByNameIgnoreCase(name)); // 200 OK
    }


    // ************************ Update - CRUD ************************ //
    @PutMapping("/{id}")
    public ResponseEntity<ProductCategoryResponseDTO> updateCategory(@PathVariable Integer id,
                                                                     @Valid @RequestBody
                                                                     ProductCategoryRequestDTO productCategoryRequestDTO) {
        ProductCategoryResponseDTO updatedCategory = productCategoryService.updatedCategory(id, productCategoryRequestDTO);
        return ResponseEntity.ok(updatedCategory); // 200 OK
    }


    // ************************ Delete - CRUD ************************ //
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        productCategoryService.deleteCategory(id);
        return ResponseEntity.noContent().build(); // 204 Not Found
    }

}
