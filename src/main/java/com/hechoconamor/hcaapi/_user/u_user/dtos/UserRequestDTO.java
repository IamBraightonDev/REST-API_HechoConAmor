package com.hechoconamor.hcaapi._user.u_user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(max = 100, message = "El nombre de usuario no puede superar los 100 caracteres")
    private String name;

    @Email(message = "El formato del correo es inválido")
    @NotBlank(message = "El correo electrónico es obligatorio")
    @Size(max = 100, message = "El correo electrónico no puede superar los 100 caracteres")
    private String email;

    @NotBlank(message = "La contrasena es obligatoria")
    @Size(max = 100, message = "El nombre de usuario no puede superar los 100 caracteres")
    private String password;

}
