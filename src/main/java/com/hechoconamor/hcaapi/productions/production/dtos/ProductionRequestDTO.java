package com.hechoconamor.hcaapi.productions.production.dtos;

import com.hechoconamor.hcaapi.productions.p_detail.dtos.ProductionDetailRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductionRequestDTO {

    private String observation;

    @NotNull(message = "Debe incluir al menos un detalle.")
    @Valid
    private List<ProductionDetailRequestDTO> details;

}
