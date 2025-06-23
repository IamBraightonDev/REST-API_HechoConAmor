package com.hechoconamor.hcaapi.role.services.impl;

import com.hechoconamor.hcaapi.role.dtos.RoleRequestDTO;
import com.hechoconamor.hcaapi.role.dtos.RoleResponseDTO;
import com.hechoconamor.hcaapi.role.entity.Role;
import com.hechoconamor.hcaapi.role.repository.RoleRepository;
import com.hechoconamor.hcaapi.role.services.RoleService;
import com.hechoconamor.hcaapi.role.validator.RoleValidator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleValidator roleValidator;
    private final ModelMapper modelMapper;

    public RoleServiceImpl(RoleRepository roleRepository,
                           RoleValidator roleValidator,
                           ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.roleValidator = roleValidator;
        this.modelMapper = modelMapper;
    }

    @Override
    public RoleResponseDTO registerRole(RoleRequestDTO requestDto) {
        roleValidator.validateBeforeRegister(requestDto);

        Role newRole = modelMapper.map(requestDto, Role.class);
        Role savedRole = roleRepository.save(newRole);

        return modelMapper.map(savedRole, RoleResponseDTO.class);
    }

    @Override
    public List<RoleResponseDTO> findAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(roles -> modelMapper.map(roles, RoleResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoleResponseDTO findById(Integer id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Rol no encontrado con ID: " + id));

        return modelMapper.map(role, RoleResponseDTO.class);
    }

    @Override
    public RoleResponseDTO findByNameIgnoreCase(String name) {
        Role role = roleRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new NoSuchElementException("Rol no encontrado con nombre: " + name));

        return modelMapper.map(role, RoleResponseDTO.class);
    }

    @Override
    public RoleResponseDTO updateRole(Integer id, RoleRequestDTO requestDto) {
        Role existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Rol no encontrado con ID: " + id));

        roleValidator.validateBeforeUpdate(id, requestDto);
        existingRole.setName(requestDto.getName());
        Role updatedRol = roleRepository.save(existingRole);

        return modelMapper.map(updatedRol, RoleResponseDTO.class);
    }

    @Override
    public void deleteRole(Integer id) {
        Role existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Rol no encontrado con ID: " + id));
        roleRepository.delete(existingRole);
    }
}
