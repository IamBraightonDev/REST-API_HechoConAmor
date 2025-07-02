package com.hechoconamor.hcaapi.productions.production.services.impl;

import com.hechoconamor.hcaapi.common.MovementType;
import com.hechoconamor.hcaapi.productions.p_detail.entity.ProductionDetail;
import com.hechoconamor.hcaapi.productions.production.dtos.ProductionRequestDTO;
import com.hechoconamor.hcaapi.productions.production.dtos.ProductionResponseDTO;
import com.hechoconamor.hcaapi.productions.production.entity.Production;
import com.hechoconamor.hcaapi.productions.production.mapper.ProductionMapper;
import com.hechoconamor.hcaapi.productions.production.repository.ProductionRepository;
import com.hechoconamor.hcaapi.productions.production.services.ProductionService;
import com.hechoconamor.hcaapi.productions.production.validator.ProductionValidator;
import com.hechoconamor.hcaapi.products.inventory_movement.entity.InventoryMovement;
import com.hechoconamor.hcaapi.products.inventory_movement.repository.InventoryMovementRepository;
import com.hechoconamor.hcaapi.supplies.s_movement.entity.SupplyMovement;
import com.hechoconamor.hcaapi.supplies.s_movement.repository.SupplyMovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductionServiceImpl implements ProductionService {

    private final ProductionValidator validator;
    private final ProductionMapper mapper;
    private final ProductionRepository productionRepository;
    private final SupplyMovementRepository supplyMovementRepository;
    private final InventoryMovementRepository inventoryMovementRepository;

    @Override
    public ProductionResponseDTO register(ProductionRequestDTO requestDTO) {
        // 1. Validación
        validator.validateBeforeRegister(requestDTO);

        // 2. Mapear DTO a entidad
        Production production = mapper.toEntity(requestDTO);
        production.setProductionDate(LocalDateTime.now());

        // 3. Guardar producción (incluye detalles)
        production = productionRepository.save(production);

        // 4. Registrar movimientos de insumos y productos
        for (ProductionDetail detail : production.getDetails()) {

            // 4.1 Salida de insumo
            SupplyMovement salida = new SupplyMovement();
            salida.setSupply(detail.getSupply());
            salida.setMovementType(MovementType.SALIDA); // Enum compartido
            salida.setQuantity(detail.getQuantityUsed());
            salida.setReason("Salida por producción ID " + production.getId());
            salida.setDate(LocalDateTime.now());
            supplyMovementRepository.save(salida);

            // 4.2 Ingreso de producto
            InventoryMovement ingreso = new InventoryMovement();
            ingreso.setProduct(detail.getProduct());
            ingreso.setType(MovementType.INGRESO); // Enum compartido
            ingreso.setQuantity(detail.getQuantityProduced());
            ingreso.setReason("Ingreso por producción ID " + production.getId());
            ingreso.setDate(LocalDateTime.now());
            inventoryMovementRepository.save(ingreso);
        }

        // ✅ Refrescar producción para que se carguen correctamente supply/product
        production = productionRepository.findById(production.getId())
                .orElseThrow(() -> new NoSuchElementException("Producción no encontrada"));

        return mapper.toResponseDTO(production);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductionResponseDTO> findAll() {
        return productionRepository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductionResponseDTO findById(Integer id) {
        Production production = productionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Producción no encontrada con ID: " + id));
        return mapper.toResponseDTO(production);
    }
}
