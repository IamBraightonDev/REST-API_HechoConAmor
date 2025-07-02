package com.hechoconamor.hcaapi.supplies.s_restock.mapper;

import com.hechoconamor.hcaapi.supplies.s_restock.dtos.SupplyRestockRequestDTO;
import com.hechoconamor.hcaapi.supplies.s_restock.dtos.SupplyRestockResponseDTO;
import com.hechoconamor.hcaapi.supplies.s_restock.entity.SupplyRestock;
import com.hechoconamor.hcaapi.supplies.supply.entity.Supply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SupplyRestockMapper {

    public SupplyRestock toEntity(SupplyRestockRequestDTO dto, Supply supply) {
        SupplyRestock restock = new SupplyRestock();
        restock.setSupply(supply);
        restock.setQuantity(dto.getQuantity());
        restock.setReason(dto.getReason());
        return restock;
    }

    public SupplyRestockResponseDTO toResponseDTO(SupplyRestock entity) {
        return SupplyRestockResponseDTO.builder()
                .id(entity.getId())
                .supplyId(entity.getSupply().getId())
                .supplyName(entity.getSupply().getName())
                .quantity(entity.getQuantity())
                .reason(entity.getReason())
                .status(entity.getStatus().name())
                .requestDate(entity.getRequestDate())
                .approvalDate(entity.getApprovalDate())
                .build();
    }

}
