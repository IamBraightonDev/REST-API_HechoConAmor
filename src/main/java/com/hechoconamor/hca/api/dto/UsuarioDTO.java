package com.hechoconamor.hca.api.dto;

import com.hechoconamor.hca.api.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private String nombre;
    private String correo;
    private String contrasena;
    private Role role;
    private LocalDateTime fechaRegistro;

}
