package com.hechoconamor.hcaapi.supplies.s_status.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplyStatusRequestDTO {

    @NotBlank(message = "El nombre es obligatorio.")
    private String name;

}
