package com.hechoconamor.hcaapi._user.u_role.validator;

import com.hechoconamor.hcaapi._user.u_role.dtos.RoleRequestDTO;
import com.hechoconamor.hcaapi._user.u_role.repository.RoleRepository;
import com.hechoconamor.hcaapi.shared.exceptions.BadRequestException;
import com.hechoconamor.hcaapi.shared.exceptions.ConflictException;
import org.springframework.stereotype.Component;

@Component
public class RoleValidator {

    private final RoleRepository roleRepository;

    public RoleValidator(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void validateBeforeRegister(RoleRequestDTO requestDto) {
        if(requestDto.getName() == null || requestDto.getName().isBlank()) {
            throw new BadRequestException("El nombre del rol no puede estar vacío.");
        }

        if(roleRepository.findByNameIgnoreCase(requestDto.getName()).isPresent()) {
            throw new ConflictException("Ya existe un rol con ese nombre.");
        }
    }

    public void validateBeforeUpdate(Integer id, RoleRequestDTO requestDto) {
        if(requestDto.getName() == null || requestDto.getName().isBlank()) {
            throw new BadRequestException("El nombre del rol no puede estar vacío.");
        }

        // Verifica si existe otro rol con ese nombre
        roleRepository.findByNameIgnoreCase(requestDto.getName()).ifPresent(existingRole -> {
            if (!existingRole.getId().equals(id)) {
                throw new ConflictException("Ya existe un rol con ese nombre.");
            }
        });
    }
}
