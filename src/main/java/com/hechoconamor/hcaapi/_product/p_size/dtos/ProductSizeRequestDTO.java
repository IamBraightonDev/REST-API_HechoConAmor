package com.hechoconamor.hcaapi._product.p_size.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSizeRequestDTO {

    @NotBlank(message = "El nombre del tamaño no puede estar vacío.")
    @Size(max = 50, message = "El nombre del tamaño no puede superar los 50 caracteres")
    private String name;

}
