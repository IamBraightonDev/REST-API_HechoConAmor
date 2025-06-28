package com.hechoconamor.hcaapi.supplies.s_category.mapper;

import com.hechoconamor.hcaapi.supplies.s_category.dtos.SupplyCategoryRequestDTO;
import com.hechoconamor.hcaapi.supplies.s_category.dtos.SupplyCategoryResponseDTO;
import com.hechoconamor.hcaapi.supplies.s_category.entity.SupplyCategory;
import org.springframework.stereotype.Component;

@Component
public class SupplyCategoryMapper {

    public SupplyCategory toEntity(SupplyCategoryRequestDTO requestDTO) {
        SupplyCategory category = new SupplyCategory();
        category.setName(requestDTO.getName());
        return category;
    }

    public SupplyCategoryResponseDTO toDTO(SupplyCategory category) {
        return new SupplyCategoryResponseDTO(category.getId(), category.getName());
    }

}
