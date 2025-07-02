package com.hechoconamor.hcaapi.orders.client.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientResponseDTO {

    private Integer id;
    private String nombre;
    private String email;
    private String telefono;
    private String direccion;
}