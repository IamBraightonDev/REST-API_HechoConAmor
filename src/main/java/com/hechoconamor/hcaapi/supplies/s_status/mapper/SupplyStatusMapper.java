package com.hechoconamor.hcaapi.supplies.s_status.mapper;

import com.hechoconamor.hcaapi.supplies.s_status.dtos.SupplyStatusRequestDTO;
import com.hechoconamor.hcaapi.supplies.s_status.dtos.SupplyStatusResponseDTO;
import com.hechoconamor.hcaapi.supplies.s_status.entity.SupplyStatus;
import org.springframework.stereotype.Component;

@Component
public class SupplyStatusMapper {

    public SupplyStatus toEntity(SupplyStatusRequestDTO requestDTO) {
        SupplyStatus entity = new SupplyStatus();
        entity.setName(requestDTO.getName());
        return entity;
    }

    public SupplyStatusResponseDTO toDTO(SupplyStatus entity) {
        return new SupplyStatusResponseDTO(entity.getId(), entity.getName());
    }

}
