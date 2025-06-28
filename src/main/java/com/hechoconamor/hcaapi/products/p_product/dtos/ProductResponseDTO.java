package com.hechoconamor.hcaapi.products.p_product.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {

    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;

    private String category;
    private String color;
    private String material;
    private String size;
    private String status;

}
