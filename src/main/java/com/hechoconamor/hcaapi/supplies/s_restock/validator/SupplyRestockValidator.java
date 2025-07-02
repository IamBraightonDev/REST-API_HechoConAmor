package com.hechoconamor.hcaapi.supplies.s_restock.validator;

import com.hechoconamor.hcaapi.shared.exceptions.BadRequestException;
import com.hechoconamor.hcaapi.supplies.s_restock.dtos.SupplyRestockRequestDTO;
import com.hechoconamor.hcaapi.supplies.supply.repository.SupplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SupplyRestockValidator {

    private final SupplyRepository supplyRepository;

    public void validateBeforeRegister(SupplyRestockRequestDTO dto) {
        if (dto.getSupplyId() == null) {
            throw new BadRequestException("El ID del insumo no puede estar vacío.");
        }

        if (!supplyRepository.existsById(dto.getSupplyId())) {
            throw new BadRequestException("El insumo con ID " + dto.getSupplyId() + " no existe.");
        }

        if (dto.getQuantity() == null || dto.getQuantity() <= 0) {
            throw new BadRequestException("La cantidad debe ser mayor a cero.");
        }
    }
}
