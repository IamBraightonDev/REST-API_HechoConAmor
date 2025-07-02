package com.hechoconamor.hcaapi.supplies.s_movement.validator;

import com.hechoconamor.hcaapi.common.MovementType;
import com.hechoconamor.hcaapi.shared.exceptions.BadRequestException;
import com.hechoconamor.hcaapi.supplies.s_movement.dtos.SupplyMovementRequestDTO;
import com.hechoconamor.hcaapi.supplies.s_movement.repository.SupplyMovementRepository;
import com.hechoconamor.hcaapi.supplies.supply.entity.Supply;
import com.hechoconamor.hcaapi.supplies.supply.repository.SupplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class SupplyMovementValidator {

    private final SupplyRepository supplyRepository;
    private final SupplyMovementRepository movementRepository;

    public void validateBeforeRegister(SupplyMovementRequestDTO requestDTO) {
        // Validar cantidad
        if (requestDTO.getQuantity() == null || requestDTO.getQuantity() <= 0) {
            throw new BadRequestException("La cantidad debe ser mayor que 0.");
        }

        // Validar tipo
        if (requestDTO.getMovementType() == null) {
            throw new BadRequestException("Debe especificarse el tipo de movimiento.");
        }

        // Validar existencia del insumo
        Supply supply = supplyRepository.findById(requestDTO.getSupplyId())
                .orElseThrow(() -> new NoSuchElementException(
                        "Insumo con ID " + requestDTO.getSupplyId() + " no encontrado."
                ));

        // Validar stock suficiente para SALIDA y AJUSTE_NEGATIVO
        if (requestDTO.getMovementType() == MovementType.SALIDA
                || requestDTO.getMovementType() == MovementType.AJUSTE_NEGATIVO) {
            Integer currentStock = movementRepository.calculateCurrentStock(supply.getId());

            if (requestDTO.getQuantity() > currentStock) {
                throw new BadRequestException(
                        "La cantidad solicitada excede el stock disponible (" + currentStock + ")."
                );
            }
        }
    }
}
