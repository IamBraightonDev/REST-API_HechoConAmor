package com.hechoconamor.hcaapi.products.inventory_movement.validator;


import com.hechoconamor.hcaapi.products.inventory_movement.dtos.InventoryMovementRequestDTO;
import com.hechoconamor.hcaapi.products.inventory_movement.repository.InventoryMovementRepository;
import com.hechoconamor.hcaapi.products.product.entity.Product;
import com.hechoconamor.hcaapi.products.product.repository.ProductRepository;
import com.hechoconamor.hcaapi.shared.exceptions.BadRequestException;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class InventoryMovementValidator {

    private final ProductRepository productRepository;
    private final InventoryMovementRepository movementRepository;

    public InventoryMovementValidator(ProductRepository productRepository,
                                      InventoryMovementRepository movementRepository) {
        this.productRepository = productRepository;
        this.movementRepository = movementRepository;
    }

    public void validateBeforeRegister(InventoryMovementRequestDTO requestDTO) {
        // Validaciones básicas
        if (requestDTO.getQuantity() == null || requestDTO.getQuantity() <= 0) {
            throw new BadRequestException("La cantidad debe ser mayor que 0.");
        }

        if (requestDTO.getType() == null) {
            throw new BadRequestException("Debe especificarse el tipo de movimiento.");
        }

        // Verificar si el producto existe
        Product product = productRepository.findById(requestDTO.getProductId())
                .orElseThrow(() -> new NoSuchElementException("Producto con ID " + requestDTO.getProductId() + " no encontrado."));

        switch (requestDTO.getType()) {
            // Tipos que RESTAN stock → validar contra stock disponible
            case SALIDA, VENTA, AJUSTE_NEGATIVO -> {
                Integer currentStock = movementRepository.findStockByProductId(product.getId());
                if (requestDTO.getQuantity() > currentStock) {
                    throw new BadRequestException("La cantidad solicitada excede el stock disponible (" + currentStock + ").");
                }
            }

            // Tipos que SUMAN stock → no necesitan validación de stock disponible
            case INGRESO, DEVOLUCION, PRODUCCION, AJUSTE_POSITIVO -> {
                // No se requiere validación de stock
            }

            // Por si el enum crece en el futuro
            default -> throw new BadRequestException("Tipo de movimiento no reconocido: " + requestDTO.getType());
        }
    }
}
