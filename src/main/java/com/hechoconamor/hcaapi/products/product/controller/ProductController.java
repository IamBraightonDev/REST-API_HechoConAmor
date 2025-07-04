package com.hechoconamor.hcaapi.products.product.controller;

import com.hechoconamor.hcaapi.products.product.dtos.ProductRequestDTO;
import com.hechoconamor.hcaapi.products.product.dtos.ProductResponseDTO;
import com.hechoconamor.hcaapi.products.product.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    // ************************ Create - CRUD ************************ //
    @PostMapping
    public ResponseEntity<ProductResponseDTO> registerProduct(@Valid @RequestBody ProductRequestDTO requestDTO) {
        ProductResponseDTO newProduct = productService.registerProduct(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct); // 201 Created
    }


    // ************************ Read - CRUD ************************ //
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAllProducts() {
        return ResponseEntity.ok(productService.findAll()); // 200 OK
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductResponseDTO>> findAllByCategory(@PathVariable String category) {
        return ResponseEntity.ok(productService.findAllByCategory(category)); // 200 OK
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(productService.findById(id)); // 200 OK
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ProductResponseDTO> findByName(@PathVariable String name) {
        return ResponseEntity.ok(productService.findByName(name)); // 200 OK
    }


    // ************************ Update - CRUD ************************ //
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Integer id,
                                                            @Valid @RequestBody ProductRequestDTO requestDTO) {
        return ResponseEntity.ok(productService.updateProduct(id, requestDTO)); // 200 OK
    }


    // ************************ Delete - CRUD ************************ //
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        productService.delete(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

}
