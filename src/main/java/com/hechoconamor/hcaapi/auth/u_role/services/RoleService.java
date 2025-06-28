package com.hechoconamor.hcaapi.auth.u_role.services;

import com.hechoconamor.hcaapi.auth.u_role.dtos.RoleRequestDTO;
import com.hechoconamor.hcaapi.auth.u_role.dtos.RoleResponseDTO;

import java.util.List;

public interface RoleService {

    RoleResponseDTO registerRole(RoleRequestDTO roleDto);

    List<RoleResponseDTO> findAllRoles();

    RoleResponseDTO findById(Integer id);

    RoleResponseDTO findByNameIgnoreCase(String name);

    RoleResponseDTO updateRole(Integer id, RoleRequestDTO role);

    void deleteRole(Integer id);

}
