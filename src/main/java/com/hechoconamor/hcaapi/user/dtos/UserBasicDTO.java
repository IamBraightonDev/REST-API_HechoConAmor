package com.hechoconamor.hcaapi.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBasicDTO {

    private Integer id;

    private String name;

    private String email;

}
