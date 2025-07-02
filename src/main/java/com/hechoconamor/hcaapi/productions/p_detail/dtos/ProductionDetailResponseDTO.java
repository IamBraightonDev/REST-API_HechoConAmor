package com.hechoconamor.hcaapi.productions.p_detail.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductionDetailResponseDTO {

    private Integer id;
    private Integer supplyId;
    private String supplyName;
    private Integer productId;
    private String productName;
    private Integer quantityUsed;
    private Integer quantityProduced;

}
