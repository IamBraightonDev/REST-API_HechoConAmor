package com.hechoconamor.hcaapi.productions.p_detail.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductionDetailRequestDTO {

    private Integer supplyId;
    private Integer productId;
    private Integer quantityUsed;
    private Integer quantityProduced;

}
