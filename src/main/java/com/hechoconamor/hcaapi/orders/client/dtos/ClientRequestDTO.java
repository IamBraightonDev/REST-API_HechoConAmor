package com.hechoconamor.hcaapi.orders.client.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no debe exceder los 100 caracteres")
    private String nombre;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe ser un email válido")
    @Size(max = 100, message = "El email no debe exceder los 100 caracteres")
    private String email;

    @Size(max = 20, message = "El teléfono no debe exceder los 20 caracteres")
    private String telefono;

    @Size(max = 200, message = "La dirección no debe exceder los 200 caracteres")
    private String direccion;
}
