package com.hechoconamor.hcaapi.user.dtos;

import com.hechoconamor.hcaapi.role.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String nombre;
    private String correo;
    private String contrasena;
    private Role role;
    private LocalDateTime fechaRegistro;

}
