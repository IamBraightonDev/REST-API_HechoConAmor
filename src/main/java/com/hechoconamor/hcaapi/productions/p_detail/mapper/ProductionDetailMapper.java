package com.hechoconamor.hcaapi.productions.p_detail.mapper;

import com.hechoconamor.hcaapi.productions.p_detail.dtos.ProductionDetailResponseDTO;
import com.hechoconamor.hcaapi.productions.p_detail.entity.ProductionDetail;
import org.springframework.stereotype.Component;

@Component
public class ProductionDetailMapper {

    public ProductionDetailResponseDTO toResponseDTO(ProductionDetail detail) {
        return ProductionDetailResponseDTO.builder()
                .id(detail.getId())
                .supplyId(detail.getSupply().getId())
                .supplyName(detail.getSupply().getName())
                .productId(detail.getProduct().getId())
                .productName(detail.getProduct().getName())
                .quantityUsed(detail.getQuantityUsed())
                .quantityProduced(detail.getQuantityProduced())
                .build();
    }

}
