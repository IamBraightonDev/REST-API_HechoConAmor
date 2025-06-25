package com.hechoconamor.hcaapi._product.p_color.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductColorRequestDTO {

    @NotBlank(message = "El nombre del p_color no puede estar vacío.")
    @Size(max = 50)
    private String name;

}
