package com.hechoconamor.hcaapi._product.p_size.validator;

import com.hechoconamor.hcaapi._product.p_size.dtos.ProductSizeRequestDTO;
import com.hechoconamor.hcaapi._product.p_size.repository.ProductSizeRepository;
import com.hechoconamor.hcaapi.shared.exceptions.BadRequestException;
import com.hechoconamor.hcaapi.shared.exceptions.ConflictException;
import org.springframework.stereotype.Component;

@Component
public class ProductSizeValidator {

    private final ProductSizeRepository productSizeRepository;

    public ProductSizeValidator(ProductSizeRepository productSizeRepository) {
        this.productSizeRepository = productSizeRepository;
    }

    public void validateBeforeRegister(ProductSizeRequestDTO productSizeRequestDTO) {
        // Validar si el nombre está vacío
        if(productSizeRequestDTO.getName() == null || productSizeRequestDTO.getName().isBlank()) {
            throw new BadRequestException("El nombre del tamaño no puede estar vacío.");
        }

        // Verificar que el nombre no exista
        if(productSizeRepository.findByNameIgnoreCase(productSizeRequestDTO.getName()).isPresent()) {
            throw new ConflictException("El nombre del tamaño ya existe.");
        }
    }

    public void validateBeforeUpdate(Integer id, ProductSizeRequestDTO productSizeRequestDTO) {
        // Validar si el nombre está vacío
        if(productSizeRequestDTO.getName() == null || productSizeRequestDTO.getName().isBlank()) {
            throw new BadRequestException("El nombre del tamaño no puede estar vacío.");
        }

        // Verificar que el nombre no exista
        productSizeRepository.findByNameIgnoreCase(productSizeRequestDTO.getName())
                .ifPresent(existingSize -> {
                    if(!existingSize.getId().equals(id)) {
                        throw new ConflictException("El nombre del tamaño ya existe.");
                    }
                });
    }
}
