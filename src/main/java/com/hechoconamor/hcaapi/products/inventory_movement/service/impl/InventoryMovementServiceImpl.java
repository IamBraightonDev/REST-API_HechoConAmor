package com.hechoconamor.hcaapi.products.inventory_movement.service.impl;

import com.hechoconamor.hcaapi.products.inventory_movement.dtos.InventoryMovementRequestDTO;
import com.hechoconamor.hcaapi.products.inventory_movement.dtos.InventoryMovementResponseDTO;
import com.hechoconamor.hcaapi.products.inventory_movement.entity.InventoryMovement;
import com.hechoconamor.hcaapi.products.inventory_movement.mapper.InventoryMovementMapper;
import com.hechoconamor.hcaapi.products.inventory_movement.repository.InventoryMovementRepository;
import com.hechoconamor.hcaapi.products.inventory_movement.service.InventoryMovementService;
import com.hechoconamor.hcaapi.products.inventory_movement.validator.InventoryMovementValidator;
import com.hechoconamor.hcaapi.products.product.entity.Product;
import com.hechoconamor.hcaapi.products.product.repository.ProductRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryMovementServiceImpl implements InventoryMovementService {

    private final InventoryMovementRepository movementRepository;
    private final ProductRepository productRepository;
    private final InventoryMovementValidator movementValidator;
    private final InventoryMovementMapper movementMapper;

    // ********** Registrar un movimiento ********** //
    @Override
    @Transactional
    public InventoryMovementResponseDTO registerMovement(InventoryMovementRequestDTO requestDTO) {
        // Validación previa
        movementValidator.validateBeforeRegister(requestDTO);

        // Buscar producto
        Product product = productRepository.findById(requestDTO.getProductId())
                .orElseThrow(() -> new NoSuchElementException("Producto con ID: " + requestDTO.getProductId() + " no encontrado."));

        // Crear movimiento con cantidad positiva
        InventoryMovement movement = new InventoryMovement();
        movement.setQuantity(requestDTO.getQuantity());
        movement.setType(requestDTO.getType());
        movement.setProduct(product);
        movement.setDate(LocalDateTime.now());
        movement.setReason(requestDTO.getReason());

        // Guardar
        InventoryMovement saved = movementRepository.save(movement);

        // Retornar DTO
        return movementMapper.toResponseDTO(saved);
    }

    // ********** Obtener todos los movimientos ********** //
    @Override
    public List<InventoryMovementResponseDTO> findAll() {
        // Buscar todos los movimientos
        List<InventoryMovement> movements = movementRepository.findAll();

        if (movements.isEmpty()) {
            throw new NoSuchElementException("No se encontraron movimientos registrados.");
        }

        // Mapear la lista de movimientos
        return movements.stream()
                .map(movementMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // ********** Buscar por ID ********** //
    @Override
    public InventoryMovementResponseDTO findById(Integer id) {
        // Buscar movimiento por su ID
        InventoryMovement movement = movementRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Movimiento con ID " + id + " no encontrado."));

        // Mapear el movimiento
        return movementMapper.toResponseDTO(movement);
    }

    // ********** Eliminar movimiento ********** //
    @Override
    @Transactional
    public void delete(Integer id) {
        // Buscar movimiento por su ID
        InventoryMovement movement = movementRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Movimiento con ID " + id + " no encontrado."));

        // Eliminar el movimiento de la base de datos
        movementRepository.delete(movement);
    }
}
