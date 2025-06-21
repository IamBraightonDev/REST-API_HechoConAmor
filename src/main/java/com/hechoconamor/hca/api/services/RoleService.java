package com.hechoconamor.hca.api.services;

import com.hechoconamor.hca.api.models.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    Role registerRole(Role role);

    List<Role> findAllRoles();

    Optional<Role> findById(Integer id);

    Optional<Role> findByNameIgnoreCase(String name);

    Optional<Role> updateRole(Integer id, Role role);

    boolean deleteRole(Integer id);

}
