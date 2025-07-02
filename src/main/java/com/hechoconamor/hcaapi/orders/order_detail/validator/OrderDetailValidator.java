package com.hechoconamor.hcaapi.orders.order_detail.validator;

import com.hechoconamor.hcaapi.orders.order_detail.dtos.OrderDetailRequestDTO;
import com.hechoconamor.hcaapi.products.inventory_movement.repository.InventoryMovementRepository;
import com.hechoconamor.hcaapi.products.product.entity.Product;
import com.hechoconamor.hcaapi.products.product.repository.ProductRepository;
import com.hechoconamor.hcaapi.shared.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderDetailValidator {

    private final ProductRepository productRepository;
    private final InventoryMovementRepository movementRepository;

    public Product validateProductAndStock(OrderDetailRequestDTO dto) {
        // 1. Validar que el producto exista
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new BadRequestException("Producto no encontrado"));

        // 2. Obtener stock actual
        Integer stock = movementRepository.findStockByProductId(product.getId());
        if (stock == null) stock = 0;

        // 3. Validar cantidad solicitada
        if (dto.getCantidad() > stock) {
            throw new BadRequestException("Stock insuficiente para el producto: " + product.getName());
        }

        return product;
    }
}
