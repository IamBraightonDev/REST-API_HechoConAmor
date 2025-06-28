package com.hechoconamor.hcaapi.products.p_product.validator;

import com.hechoconamor.hcaapi.products.p_product.dtos.ProductRequestDTO;
import com.hechoconamor.hcaapi.products.p_product.repository.ProductRepository;
import com.hechoconamor.hcaapi.shared.exceptions.BadRequestException;
import com.hechoconamor.hcaapi.shared.exceptions.ConflictException;
import org.springframework.stereotype.Component;

@Component
public class ProductValidator {

    private final ProductRepository productRepository;

    public ProductValidator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void validateBeforeRegister(ProductRequestDTO dto) {
        // Validar si el nombre está vacío
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new BadRequestException("El nombre del producto no puede estar vacío.");
        }

        // Verificar si existe otro material con el mismo nombre
        if (productRepository.findByNameIgnoreCase(dto.getName()).isPresent()) {
            throw new ConflictException("El nombre del producto ya existe.");
        }

        // Puedes agregar más validaciones si lo deseas (precio mínimo, stock >= 0, etc.)
    }

    public void validateBeforeUpdate(Integer id, ProductRequestDTO dto) {
        // Validar si el nombre está vacío
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new BadRequestException("El nombre del producto no puede estar vacío.");
        }

        // Verificar si existe otro material con el mismo nombre
        productRepository.findByNameIgnoreCase(dto.getName())
                .ifPresent(existingProduct -> {
                    if (!existingProduct.getId().equals(id)) {
                        throw new ConflictException("El nombre del producto ya está en uso.");
                    }
                });
    }

}
