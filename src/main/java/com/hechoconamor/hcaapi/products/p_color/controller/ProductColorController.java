package com.hechoconamor.hcaapi.products.p_color.controller;

import com.hechoconamor.hcaapi.products.p_color.dtos.ProductColorRequestDTO;
import com.hechoconamor.hcaapi.products.p_color.dtos.ProductColorResponseDTO;
import com.hechoconamor.hcaapi.products.p_color.services.ProductColorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product/colors")
public class ProductColorController {

    private final ProductColorService productColorService;

    public ProductColorController(ProductColorService productColorService) {
        this.productColorService = productColorService;
    }

    // ************************ Create - CRUD ************************ //
    @PostMapping
    public ResponseEntity<ProductColorResponseDTO> registerProduct(@Valid @RequestBody
                                                                   ProductColorRequestDTO productColorRequestDTO) {
        ProductColorResponseDTO newProductColorResponseDTO = productColorService.registerColor(productColorRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProductColorResponseDTO); // 201 Created
    }


    // ************************ Read - CRUD ************************ //
    @GetMapping
    public ResponseEntity<List<ProductColorResponseDTO>> findAllColors() {
        return ResponseEntity.ok(productColorService.findAll()); // 200 OK
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ProductColorResponseDTO> findColorById(@PathVariable Integer id) {
        return ResponseEntity.ok(productColorService.findById(id)); // 200 OK
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ProductColorResponseDTO> findColorByName(@PathVariable String name) {
        return ResponseEntity.ok(productColorService.findByName(name)); // 200 OK
    }


    // ************************ Update - CRUD ************************ //
    @PutMapping("/{id}")
    public ResponseEntity<ProductColorResponseDTO> updateColor(@PathVariable Integer id,
                                                               @Valid @RequestBody
                                                               ProductColorRequestDTO productColorRequestDTO) {
        ProductColorResponseDTO updatedColor = productColorService.updateColor(id, productColorRequestDTO);
        return ResponseEntity.ok(updatedColor); // 200 OK
    }


    // ************************ Delete - CRUD ************************ //
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteColor(@PathVariable Integer id) {
        productColorService.deleteColor(id);
        return ResponseEntity.noContent().build(); // 204 Not Found
    }

}
