package com.hechoconamor.hcaapi.productions.p_detail.validator;

import com.hechoconamor.hcaapi.productions.p_detail.dtos.ProductionDetailRequestDTO;
import com.hechoconamor.hcaapi.products.product.repository.ProductRepository;
import com.hechoconamor.hcaapi.shared.exceptions.BadRequestException;
import com.hechoconamor.hcaapi.supplies.supply.repository.SupplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductionDetailValidator {

    private final SupplyRepository supplyRepository;
    private final ProductRepository productRepository;

    public void validateBeforeRegister(ProductionDetailRequestDTO dto) {
        // Validar insumo
        if (dto.getSupplyId() == null) {
            throw new BadRequestException("El ID del insumo no puede estar vacío.");
        }
        if (!supplyRepository.existsById(dto.getSupplyId())) {
            throw new BadRequestException("El insumo con ID " + dto.getSupplyId() + " no existe.");
        }

        // Validar producto
        if (dto.getProductId() == null) {
            throw new BadRequestException("El ID del producto no puede estar vacío.");
        }
        if (!productRepository.existsById(dto.getProductId())) {
            throw new BadRequestException("El producto con ID " + dto.getProductId() + " no existe.");
        }

        // Validar cantidad usada
        if (dto.getQuantityUsed() == null || dto.getQuantityUsed() <= 0) {
            throw new BadRequestException("La cantidad usada de insumo debe ser mayor a cero.");
        }

        // Validar cantidad producida
        if (dto.getQuantityProduced() == null || dto.getQuantityProduced() < 0) {
            throw new BadRequestException("La cantidad producida no puede ser negativa.");
        }
    }
}
