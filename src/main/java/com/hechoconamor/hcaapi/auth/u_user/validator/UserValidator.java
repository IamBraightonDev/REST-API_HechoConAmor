package com.hechoconamor.hcaapi.auth.u_user.validator;

import com.hechoconamor.hcaapi.shared.exceptions.BadRequestException;
import com.hechoconamor.hcaapi.shared.exceptions.ConflictException;
import com.hechoconamor.hcaapi.auth.u_user.dtos.UserRequestDTO;
import com.hechoconamor.hcaapi.auth.u_user.repositoy.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    private final UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateBeforeRegister(UserRequestDTO userDto) {
        // Validar nombre
        if(userDto.getName() == null || userDto.getName().isBlank()) {
            throw new BadRequestException("El nombre de usuario no puede estar vacío.");
        }

        // Verifica si existe otro usuario con ese nombre
        if(userRepository.findByNameIgnoreCase(userDto.getName()).isPresent()) {
            throw new ConflictException("Ya existe un usuario con ese nombre.");
        }

        // Validar email
        if (userDto.getEmail() == null || userDto.getEmail().isBlank()) {
            throw new BadRequestException("El correo electrónico no puede estar vacío.");
        }

        // Verifica si existe otro usuario con ese email
        if (userRepository.findByEmailIgnoreCase(userDto.getEmail()).isPresent()) {
            throw new ConflictException("Ya existe un usuario con ese correo electrónico.");
        }

    }

    public void validateBeforeUpdate(Integer id, UserRequestDTO userDto) {
        // Validar nombre
        if(userDto.getName() == null || userDto.getName().isBlank()) {
            throw new BadRequestException("El nombre de usuario no puede estar vacío.");
        }

        // Verifica si existe otro usuario con ese nombre
        userRepository.findByNameIgnoreCase(userDto.getName()).ifPresent(existingUser -> {
            if (!existingUser.getId().equals(id)) {
                throw new ConflictException("Ya existe un usuario con ese nombre.");
            }
        });

        // Validar email
        if (userDto.getEmail() == null || userDto.getEmail().isBlank()) {
            throw new BadRequestException("El correo electrónico no puede estar vacío.");
        }

        // Verifica si existe otro usuario con ese email
        userRepository.findByEmailIgnoreCase(userDto.getEmail()).ifPresent(existingUser -> {
            if (!existingUser.getId().equals(id)) {
                throw new ConflictException("Ya existe un usuario con ese correo electrónico.");
            }
        });
    }
}
