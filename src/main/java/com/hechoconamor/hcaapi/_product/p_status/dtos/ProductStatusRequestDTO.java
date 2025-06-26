package com.hechoconamor.hcaapi._product.p_status.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductStatusRequestDTO {

    @NotBlank(message = "El nombre del estado no puede estar vacío.")
    @Size(max = 50, message = "El nombre del estado no puede superar los 50 caracteres.")
    private String name;

}
