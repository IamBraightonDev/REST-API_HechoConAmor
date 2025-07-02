package com.hechoconamor.hcaapi.supplies.s_movement.services.impl;

import com.hechoconamor.hcaapi.supplies.s_movement.dtos.SupplyMovementRequestDTO;
import com.hechoconamor.hcaapi.supplies.s_movement.dtos.SupplyMovementResponseDTO;
import com.hechoconamor.hcaapi.supplies.s_movement.entity.SupplyMovement;
import com.hechoconamor.hcaapi.supplies.s_movement.mapper.SupplyMovementMapper;
import com.hechoconamor.hcaapi.supplies.s_movement.repository.SupplyMovementRepository;
import com.hechoconamor.hcaapi.supplies.s_movement.services.SupplyMovementService;
import com.hechoconamor.hcaapi.supplies.s_movement.validator.SupplyMovementValidator;
import com.hechoconamor.hcaapi.supplies.supply.entity.Supply;
import com.hechoconamor.hcaapi.supplies.supply.repository.SupplyRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplyMovementServiceImpl implements SupplyMovementService {

    private final SupplyMovementRepository movementRepository;
    private final SupplyRepository supplyRepository;
    private final SupplyMovementValidator movementValidator;
    private final SupplyMovementMapper movementMapper;

    // ********** Registrar un movimiento ********** //
    @Override
    @Transactional
    public SupplyMovementResponseDTO registerMovement(SupplyMovementRequestDTO dto) {
        // Validación
        movementValidator.validateBeforeRegister(dto);

        // Buscar insumo
        Supply supply = supplyRepository.findById(dto.getSupplyId())
                .orElseThrow(() -> new NoSuchElementException(
                        "Insumo con ID " + dto.getSupplyId() + " no encontrado."
                ));

        // Crear movimiento
        SupplyMovement movement = new SupplyMovement();
        movement.setSupply(supply);
        movement.setMovementType(dto.getMovementType());
        movement.setQuantity(dto.getQuantity()); // Siempre positivo
        movement.setReason(dto.getReason());
        movement.setDate(LocalDateTime.now());

        // Guardar
        SupplyMovement saved = movementRepository.save(movement);

        // Retornar DTO limpio
        return movementMapper.toResponseDTO(saved);
    }

    // ********** Obtener todos los movimientos ********** //
    @Override
    public List<SupplyMovementResponseDTO> findAll() {
        List<SupplyMovement> movements = movementRepository.findAll();

        if (movements.isEmpty()) {
            throw new NoSuchElementException("No se encontraron movimientos registrados.");
        }

        return movements.stream()
                .map(movementMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // ********** Buscar por ID ********** //
    @Override
    public SupplyMovementResponseDTO findById(Integer id) {
        SupplyMovement movement = movementRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Movimiento con ID " + id + " no encontrado."));

        return movementMapper.toResponseDTO(movement);
    }

    // ********** Eliminar movimiento ********** //
    @Override
    @Transactional
    public void delete(Integer id) {
        SupplyMovement movement = movementRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Movimiento con ID " + id + " no encontrado."));

        movementRepository.delete(movement);
    }
}
