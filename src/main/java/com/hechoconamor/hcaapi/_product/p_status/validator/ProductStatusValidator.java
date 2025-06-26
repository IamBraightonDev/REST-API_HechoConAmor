package com.hechoconamor.hcaapi._product.p_status.validator;

import com.hechoconamor.hcaapi._product.p_status.dtos.ProductStatusRequestDTO;
import com.hechoconamor.hcaapi._product.p_status.repository.ProductStatusRepository;
import com.hechoconamor.hcaapi.shared.exceptions.BadRequestException;
import com.hechoconamor.hcaapi.shared.exceptions.ConflictException;
import org.springframework.stereotype.Component;

@Component
public class ProductStatusValidator {

    private final ProductStatusRepository productStatusRepository;

    public ProductStatusValidator(ProductStatusRepository productStatusRepository) {
        this.productStatusRepository = productStatusRepository;
    }

    public void validateBeforeRegister(ProductStatusRequestDTO productStatusRequestDTO) {
        // Validar si el nombre está vacío
        if(productStatusRequestDTO.getName() == null || productStatusRequestDTO.getName().isBlank()) {
            throw new BadRequestException("El nombre del estado no puede estar vacío.");
        }

        // Verificar si el nombre ya existe
        if(productStatusRepository.findByNameIgnoreCase(productStatusRequestDTO.getName()).isPresent()) {
            throw new ConflictException("El nombre del estado ya existe.");
        }
    }

    public void validateBeforeUpdate(Integer id, ProductStatusRequestDTO productStatusRequestDTO) {
        // Validar si el nombre está vacío
        if(productStatusRequestDTO.getName() == null || productStatusRequestDTO.getName().isBlank()) {
            throw new BadRequestException("El nombre del estado no puede estar vacío.");
        }

        // Verificar si el nombre ya existe
        productStatusRepository.findByNameIgnoreCase(productStatusRequestDTO.getName())
                .ifPresent(existingStatus -> {
                    if(!existingStatus.getId().equals(id)) {
                        throw new ConflictException("El nombre del estado ya existe");
                    }
                });
    }
}
