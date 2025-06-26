package com.hechoconamor.hcaapi._product.p_material.validator;

import com.hechoconamor.hcaapi._product.p_material.dtos.ProductMaterialRequestDTO;
import com.hechoconamor.hcaapi._product.p_material.repository.ProductMaterialRepository;
import com.hechoconamor.hcaapi.shared.exceptions.BadRequestException;
import com.hechoconamor.hcaapi.shared.exceptions.ConflictException;
import org.springframework.stereotype.Component;

@Component
public class ProductMaterialValidator {

    private final ProductMaterialRepository productMaterialRepository;

    public ProductMaterialValidator(ProductMaterialRepository productMaterialRepository) {
        this.productMaterialRepository = productMaterialRepository;
    }

    public void validateBeforeRegister(ProductMaterialRequestDTO productMaterialRequestDTO) {
        // Validar si el nombre está vacío
        if(productMaterialRequestDTO.getName() == null || productMaterialRequestDTO.getName().isBlank()) {
            throw new BadRequestException("El nombre del material no puede estar vacío.");
        }

        // Verificar si existe otro material con el mismo nombre
        if(productMaterialRepository.findByNameIgnoreCase(productMaterialRequestDTO.getName()).isPresent()) {
            throw new ConflictException("El nombre del material ya exise.");
        }
    }

    public void validateBeforeUpdate(Integer id, ProductMaterialRequestDTO productMaterialRequestDTO) {
        // Validar si el nombre está vacío
        if(productMaterialRequestDTO.getName() == null || productMaterialRequestDTO.getName().isBlank()) {
            throw new BadRequestException("El nombre del material no puede estar vacío.");
        }

        // Verificar si existe otro material con el mismo nombre
        productMaterialRepository.findByNameIgnoreCase(productMaterialRequestDTO.getName())
                .ifPresent(existingMaterial -> {
                    if(!existingMaterial.getId().equals(id)) {
                        throw new ConflictException("El nombre del material ya existe");
                    }
                });
    }
}
