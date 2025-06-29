package com.hechoconamor.hcaapi.supplies.s_status.validator;

import com.hechoconamor.hcaapi.shared.exceptions.BadRequestException;
import com.hechoconamor.hcaapi.shared.exceptions.ConflictException;
import com.hechoconamor.hcaapi.supplies.s_category.repository.SupplyCategoryRepository;
import com.hechoconamor.hcaapi.supplies.s_status.dtos.SupplyStatusRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SupplyStatusValidator {

    private final SupplyCategoryRepository categoryRepository;

    public void validateBeforeRegister(SupplyStatusRequestDTO requestDTO) {
        // Validar si el nombre está vacío
        if(requestDTO.getName() == null || requestDTO.getName().isBlank()) {
            throw new BadRequestException("El nombre del estado no puede estar vacío.");
        }

        // Verificar si el nombre ya existe
        if(categoryRepository.findByNameIgnoreCase(requestDTO.getName()).isPresent()) {
            throw new ConflictException("El nombre del estado ya existe.");
        }
    }

    public void validateBeforeUpdate(Integer id, SupplyStatusRequestDTO requestDTO) {
        // Validar si el nombre está vacío
        if(requestDTO.getName() == null || requestDTO.getName().isBlank()) {
            throw new BadRequestException("El nombre del estado no puede estar vacío.");
        }

        // Verificar si el nombre ya existe
        categoryRepository.findByNameIgnoreCase(requestDTO.getName())
                .ifPresent(existingStatus -> {
                    if(!existingStatus.getId().equals(id)) {
                        throw new ConflictException("El nombre del estado ya existe");
                    }
                });
    }
}
