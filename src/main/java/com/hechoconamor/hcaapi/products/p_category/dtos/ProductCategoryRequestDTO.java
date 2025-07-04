package com.hechoconamor.hcaapi.products.p_category.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryRequestDTO {

    @NotBlank(message = "El nombre de la categoría es obligatorio")
    @Size(max = 100)
    private String name;

}
