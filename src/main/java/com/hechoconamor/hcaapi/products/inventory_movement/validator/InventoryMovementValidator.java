package com.hechoconamor.hcaapi.products.inventory_movement.validator;

import com.hechoconamor.hcaapi.products.inventory_movement.dtos.InventoryMovementRequestDTO;
import com.hechoconamor.hcaapi.products.inventory_movement.enums.MovementType;
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
        // Validación básica redundante (refuerzo)
        if (requestDTO.getQuantity() == null || requestDTO.getQuantity() <= 0) {
            throw new BadRequestException("La cantidad debe ser mayor que 0.");
        }

        if (requestDTO.getType() == null) {
            throw new BadRequestException("Debe especificarse el tipo de movimiento.");
        }

        // Verificar si el producto existe
        Product product = productRepository.findById(requestDTO.getProductId())
                .orElseThrow(() -> new NoSuchElementException("Producto con ID " + requestDTO.getProductId() + " no encontrado."));

        // Validación especial: evitar salidas de stock superiores al disponible
        if (requestDTO.getType() == MovementType.SALIDA || requestDTO.getType() == MovementType.VENTA || requestDTO.getType() == MovementType.AJUSTE) {
            Integer currentStock = movementRepository.findStockByProductId(product.getId());

            if (requestDTO.getQuantity() > currentStock) {
                throw new BadRequestException("La cantidad solicitada excede el stock disponible (" + currentStock + ").");
            }
        }
    }
}
