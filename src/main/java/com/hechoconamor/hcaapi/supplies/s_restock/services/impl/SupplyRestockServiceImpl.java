package com.hechoconamor.hcaapi.supplies.s_restock.services.impl;

import com.hechoconamor.hcaapi.common.MovementType;
import com.hechoconamor.hcaapi.shared.exceptions.BadRequestException;
import com.hechoconamor.hcaapi.supplies.s_movement.entity.SupplyMovement;
import com.hechoconamor.hcaapi.supplies.s_movement.repository.SupplyMovementRepository;
import com.hechoconamor.hcaapi.supplies.s_restock.dtos.SupplyRestockRequestDTO;
import com.hechoconamor.hcaapi.supplies.s_restock.dtos.SupplyRestockResponseDTO;
import com.hechoconamor.hcaapi.supplies.s_restock.entity.SupplyRestock;
import com.hechoconamor.hcaapi.supplies.s_restock.enums.RestockStatus;
import com.hechoconamor.hcaapi.supplies.s_restock.mapper.SupplyRestockMapper;
import com.hechoconamor.hcaapi.supplies.s_restock.repository.SupplyRestockRepository;
import com.hechoconamor.hcaapi.supplies.s_restock.services.SupplyRestockService;
import com.hechoconamor.hcaapi.supplies.s_restock.validator.SupplyRestockValidator;
import com.hechoconamor.hcaapi.supplies.supply.entity.Supply;
import com.hechoconamor.hcaapi.supplies.supply.repository.SupplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class SupplyRestockServiceImpl implements SupplyRestockService {

    private final SupplyRestockValidator validator;
    private final SupplyRestockMapper mapper;
    private final SupplyRestockRepository restockRepository;
    private final SupplyRepository supplyRepository;
    private final SupplyMovementRepository movementRepository;

    @Override
    public SupplyRestockResponseDTO register(SupplyRestockRequestDTO dto) {
        validator.validateBeforeRegister(dto);

        Supply supply = supplyRepository.findById(dto.getSupplyId())
                .orElseThrow(() -> new NoSuchElementException("Supply no encontrado"));

        SupplyRestock restock = mapper.toEntity(dto, supply);
        restock.setStatus(RestockStatus.PENDIENTE);
        restock.setRequestDate(LocalDateTime.now());

        restock = restockRepository.save(restock);
        return mapper.toResponseDTO(restock);
    }

    @Override
    public SupplyRestockResponseDTO approve(Integer id) {
        SupplyRestock restock = restockRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Reposición no encontrada"));

        if (restock.getStatus() != RestockStatus.PENDIENTE) {
            throw new BadRequestException("La reposición ya fue procesada.");
        }

        restock.setStatus(RestockStatus.APROBADO);
        restock.setApprovalDate(LocalDateTime.now());

        // Registrar movimiento de ingreso
        SupplyMovement ingreso = new SupplyMovement();
        ingreso.setSupply(restock.getSupply());
        ingreso.setQuantity(restock.getQuantity());
        ingreso.setDate(LocalDateTime.now());
        ingreso.setMovementType(MovementType.INGRESO);
        ingreso.setReason("Reposición aprobada (ID " + restock.getId() + ")");
        movementRepository.save(ingreso);

        restock = restockRepository.save(restock);
        return mapper.toResponseDTO(restock);
    }

    @Override
    public SupplyRestockResponseDTO reject(Integer id, String reason) {
        SupplyRestock restock = restockRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Reposición no encontrada"));

        if (restock.getStatus() != RestockStatus.PENDIENTE) {
            throw new BadRequestException("La reposición ya fue procesada.");
        }

        restock.setStatus(RestockStatus.RECHAZADO);
        restock.setReason(reason);
        restock.setApprovalDate(LocalDateTime.now());

        restock = restockRepository.save(restock);
        return mapper.toResponseDTO(restock);
    }

    @Override
    public List<SupplyRestockResponseDTO> findAll() {
        return restockRepository.findAll().stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    @Override
    public SupplyRestockResponseDTO findById(Integer id) {
        SupplyRestock restock = restockRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Reposición no encontrada"));
        return mapper.toResponseDTO(restock);
    }
}
