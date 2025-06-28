package com.hechoconamor.hcaapi.supplies.s_category.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplyCategoryRequestDTO {

    @NotBlank(message = "El nombre de la categoría es obligatoria.")
    private String name;

}
