package com.hechoconamor.hcaapi._product.p_material.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductMaterialRequestDTO {

    @NotBlank(message = "El nombre no puede estar vacío.")
    private String name;

}
