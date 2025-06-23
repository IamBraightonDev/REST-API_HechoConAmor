package com.hechoconamor.hcaapi.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private Integer id;

    private String name;

    private String email;

    private String roleName;

    private LocalDateTime registrationDate;

}
