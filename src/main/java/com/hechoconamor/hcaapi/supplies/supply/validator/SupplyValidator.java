package com.hechoconamor.hcaapi.supplies.supply.validator;

import com.hechoconamor.hcaapi.shared.exceptions.BadRequestException;
import com.hechoconamor.hcaapi.shared.exceptions.ConflictException;
import com.hechoconamor.hcaapi.supplies.s_category.repository.SupplyCategoryRepository;
import com.hechoconamor.hcaapi.supplies.s_status.repository.SupplyStatusRepository;
import com.hechoconamor.hcaapi.supplies.supply.dtos.SupplyRequestDTO;
import com.hechoconamor.hcaapi.supplies.supply.repository.SupplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SupplyValidator {

    private final SupplyRepository supplyRepository;
    private final SupplyCategoryRepository categoryRepository;
    private final SupplyStatusRepository statusRepository;

    public void validateBeforeRegister(SupplyRequestDTO requestDTO) {
        // Validar si el nombre está vacío
        if (requestDTO.getName() == null || requestDTO.getName().isBlank()) {
            throw new BadRequestException("El nombre del insumo no puede estar vacío.");
        }

        // Validar si la descripción está vacía
        if (requestDTO.getDescription() == null || requestDTO.getDescription().isBlank()) {
            throw new BadRequestException("La descripción del insumo no puede estar vacía.");
        }

        // Validar si la categoría existe
        if (!categoryRepository.existsById(requestDTO.getCategoryId())) {
            throw new BadRequestException("La categoría especificada no existe.");
        }

        // Validar si el estado existe
        if (!statusRepository.existsById(requestDTO.getStatusId())) {
            throw new BadRequestException("El estado especificado no existe.");
        }

        // Verificar si ya existe otro insumo con el mismo nombre
        if (supplyRepository.findByNameIgnoreCase(requestDTO.getName()).isPresent()) {
            throw new ConflictException("Ya existe un insumo con el nombre: " + requestDTO.getName());
        }
    }

    public void validateBeforeUpdate(Integer id, SupplyRequestDTO requestDTO) {
        // Validar si el nombre está vacío
        if (requestDTO.getName() == null || requestDTO.getName().isBlank()) {
            throw new BadRequestException("El nombre del insumo no puede estar vacío.");
        }

        // Validar si la descripción está vacía
        if (requestDTO.getDescription() == null || requestDTO.getDescription().isBlank()) {
            throw new BadRequestException("La descripción del insumo no puede estar vacía.");
        }

        // Validar si la categoría existe
        if (!categoryRepository.existsById(requestDTO.getCategoryId())) {
            throw new BadRequestException("La categoría especificada no existe.");
        }

        // Validar si el estado existe
        if (!statusRepository.existsById(requestDTO.getStatusId())) {
            throw new BadRequestException("El estado especificado no existe.");
        }

        // Verificar si existe otro insumo con el mismo nombre
        supplyRepository.findByNameIgnoreCase(requestDTO.getName())
                .ifPresent(existing -> {
                    if (!existing.getId().equals(id)) {
                        throw new ConflictException("El nombre del insumo ya está en uso por otro registro.");
                    }
                });
    }

}
