package com.hechoconamor.hca.api.role.services.impl;

import com.hechoconamor.hca.api.role.entity.Role;
import com.hechoconamor.hca.api.role.repository.RoleRepository;
import com.hechoconamor.hca.api.role.services.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role registerRole(Role role) {
        if (roleRepository.findByNameIgnoreCase(role.getName()).isPresent()) {      // Validar si el nombre ya existe
            throw new IllegalArgumentException("El nombre de este rol ya existe");
        }
        return roleRepository.save(role);
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(Integer id) {
        return roleRepository.findById(id);
    }

    @Override
    public Optional<Role> findByNameIgnoreCase(String name) {
        return roleRepository.findByNameIgnoreCase(name);
    }

    @Override
    public Optional<Role> updateRole(Integer id, Role role) {
        return roleRepository.findById(id)
                .map(existingRole -> {
                    existingRole.setName(role.getName());
                    return roleRepository.save(existingRole);
                });
        // Si el repositorio no encuentra el role, .map no se ejecuta y devuelve Optional.empty()
    }

    @Override
    public boolean deleteRole(Integer id) {
        return roleRepository.findById(id)
                .map(rol -> {
                    roleRepository.deleteById(id);
                    return true;
                })
                .orElse(false);
    }
}
