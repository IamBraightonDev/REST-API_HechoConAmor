package com.hechoconamor.hcaapi.supplies.supply.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupplyResponseDTO {

    private Integer id;
    private String name;
    private String description;

    private Integer stock;

    private String category;
    private String status;

}
