package com.hechoconamor.hcaapi.supplies.supply.mapper;

import com.hechoconamor.hcaapi.supplies.supply.dtos.SupplyResponseDTO;
import com.hechoconamor.hcaapi.supplies.supply.entity.Supply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SupplyMapper {

    public SupplyResponseDTO toResponseDTO(Supply supply) {
        return SupplyResponseDTO.builder()
                .id(supply.getId())
                .name(supply.getName())
                .description(supply.getDescription())
                .stock(supply.getStock())
                .category(supply.getCategory().getName())
                .status(supply.getStatus().getName())
                .build();
    }

}
