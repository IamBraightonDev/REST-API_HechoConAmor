package com.hechoconamor.hcaapi.supplies.s_category.validator;

import com.hechoconamor.hcaapi.products.p_category.dtos.ProductCategoryRequestDTO;
import com.hechoconamor.hcaapi.shared.exceptions.BadRequestException;
import com.hechoconamor.hcaapi.shared.exceptions.ConflictException;
import com.hechoconamor.hcaapi.supplies.s_category.dtos.SupplyCategoryRequestDTO;
import com.hechoconamor.hcaapi.supplies.s_category.repository.SupplyCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SupplyCategoryValidator {

    private final SupplyCategoryRepository repository;

    public void validateBeforeRegister(SupplyCategoryRequestDTO requestDTO) {
        // Validar nombre
        if(requestDTO.getName() == null || requestDTO.getName().isBlank()) {
            throw new BadRequestException("El nombre de la categoría no puede estar vacío.");
        }

        // Verifica si existe otra categoría con el mismo nombre
        if(repository.findByNameIgnoreCase(requestDTO.getName()).isPresent()) {
            throw new ConflictException("El nombre de la categoría ya existe.");
        }
    }

    public void validateBeforeUpdate(Integer id, SupplyCategoryRequestDTO requestDTO) {
        // Validar nombre
        if(requestDTO.getName() == null || requestDTO.getName().isBlank()) {
            throw new BadRequestException("El nombre de la categoría no puede estar vacío");
        }

        // Verifica si existe otra categoría con el mismo nombre
        repository.findByNameIgnoreCase(requestDTO.getName())
                .ifPresent(existingCategory -> {
                    if(!existingCategory.getId().equals(id)) {
                        throw new ConflictException("El nombre de la categoría ya existe");
                    }
                });
    }
}
