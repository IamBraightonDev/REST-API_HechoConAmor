package com.hechoconamor.hcaapi.products.p_color.validator;

import com.hechoconamor.hcaapi.products.p_color.dtos.ProductColorRequestDTO;
import com.hechoconamor.hcaapi.products.p_color.repository.ProductColorRepository;
import com.hechoconamor.hcaapi.shared.exceptions.BadRequestException;
import com.hechoconamor.hcaapi.shared.exceptions.ConflictException;
import org.springframework.stereotype.Component;

@Component
public class ProductColorValidator {

    private final ProductColorRepository productColorRepository;

    public ProductColorValidator(ProductColorRepository productColorRepository) {
        this.productColorRepository = productColorRepository;
    }

    public void validateBeforeRegister(ProductColorRequestDTO productColorRequestDTO) {
        // Validar el nombre
        if(productColorRequestDTO.getName() == null || productColorRequestDTO.getName().isBlank()) {
            throw new BadRequestException("El nombre del color no puede estar vacío.");
        }

        // Verifica si existe otro color con el mismo nombre
        if(productColorRepository.findByNameIgnoreCase(productColorRequestDTO.getName()).isPresent()) {
            throw new ConflictException("El nombre del color ya existe.");
        }
    }

    public void validateBeforeUpdate(Integer id, ProductColorRequestDTO productColorRequestDTO) {
        // Validar el nombre
        if(productColorRequestDTO.getName() == null || productColorRequestDTO.getName().isBlank()) {
            throw new BadRequestException("El nombre del color no puede estar vacío.");
        }

        // Verifica si existe otro color con el mismo nombre
        productColorRepository.findByNameIgnoreCase(productColorRequestDTO.getName())
                .ifPresent(existingColor -> {
                    if(!existingColor.getId().equals(id)) {
                        throw new ConflictException("El nombre del color ya existe.");
                    }
                });
    }
}
