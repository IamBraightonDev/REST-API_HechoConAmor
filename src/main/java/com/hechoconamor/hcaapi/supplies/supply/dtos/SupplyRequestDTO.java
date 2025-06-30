package com.hechoconamor.hcaapi.supplies.supply.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplyRequestDTO {

    @NotBlank(message = "El nombre del insumo es obligatorio.")
    @Size(max = 100, message = "El nombre no debe exceder los 100 caracteres.")
    private String name;

    @NotBlank(message = "La descripción es obligatoria.")
    @Size(max = 255, message = "La descripción no debe exceder los 255 caracteres.")
    private String description;

    @NotNull(message = "Debe seleccionar una categoría.")
    private Integer categoryId;

    @NotNull(message = "Debe seleccionar un estado.")
    private Integer statusId;

}
