package com.hechoconamor.hcaapi.productions.production.dtos;

import com.hechoconamor.hcaapi.productions.p_detail.dtos.ProductionDetailResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductionResponseDTO {

    private Integer id;
    private LocalDateTime productionDate;
    private String observation;
    private List<ProductionDetailResponseDTO> details;

}
