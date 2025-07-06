package com.hechoconamor.hcaapi.products.product.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {

    @NotBlank(message = "El nombre del producto es obligatorio.")
    @Size(max = 150, message = "El producto no puede superar los 150 caracteres.")
    private String name;

    @NotBlank(message = "La descripción del producto es obligatoria.")
    private String description;

    @NotNull(message = "El precio es obligatorio.")
    @DecimalMin(value = "0.00", inclusive = false, message = "El precio debe ser mayor a 0.")
    private BigDecimal price;

    private String imageUrl;

    @NotNull(message = "Debe seleccionar una categoría.")
    private Integer categoryId;

    @NotNull(message = "Debe seleccionar un color.")
    private Integer colorId;

    @NotNull(message = "Debe seleccionar un material.")
    private Integer materialId;

    @NotNull(message = "Debe seleccionar un tamaño.")
    private Integer sizeId;

    @NotNull(message = "Debe seleccionar un estado.")
    private Integer statusId;
}
