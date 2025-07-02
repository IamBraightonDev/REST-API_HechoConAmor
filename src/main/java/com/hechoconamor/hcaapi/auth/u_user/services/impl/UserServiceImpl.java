package com.hechoconamor.hcaapi.auth.u_user.services.impl;

import com.hechoconamor.hcaapi.auth.u_role.entity.Role;
import com.hechoconamor.hcaapi.auth.u_role.repository.RoleRepository;
import com.hechoconamor.hcaapi.auth.u_user.dtos.UserRequestDTO;
import com.hechoconamor.hcaapi.auth.u_user.dtos.UserResponseDTO;
import com.hechoconamor.hcaapi.auth.u_user.entity.User;
import com.hechoconamor.hcaapi.auth.u_user.repositoy.UserRepository;
import com.hechoconamor.hcaapi.auth.u_user.services.UserService;
import com.hechoconamor.hcaapi.auth.u_user.validator.UserValidator;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;        // Repositorio para operaciones CRUD con la entidad User
    private final RoleRepository roleRepository;        // Repositorio para acceder a los roles relacionados al usuario
    private final UserValidator userValidator;          // Clase de validación personalizada para lógica de negocio
    private final ModelMapper modelMapper;              // Herramienta para convertir entre entidades y DTOs

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           UserValidator userValidator,
                           ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userValidator = userValidator;
        this.modelMapper = modelMapper;
    }

    // ********** Registrar un nuevo usuario en el sistema ********** //
    @Override
    public UserResponseDTO registerUser(UserRequestDTO userDto) {
        // Validar reglas de negocio antes del registro
        userValidator.validateBeforeRegister(userDto);

        // Obtener el rol usando el ID proporcionado en el DTO
        Role defaultRole = roleRepository.findByNameIgnoreCase("Vendedor")
                .orElseThrow(() -> new NoSuchElementException("Rol no encontrado"));

        // Convertir el DTO a una entidad User
        User newUser = modelMapper.map(userDto, User.class);
        newUser.setRole(defaultRole); // Asignar el rol a la entidad User
        newUser.setId(null); // Forzar que Hibernate lo trate como un nuevo registro

        // Guardar el nuevo usuario en la base de datos
        User savedUser = userRepository.save(newUser);

        // Convertir la entidad guardada a un DTO de respuesta y retornarlo
        return modelMapper.map(savedUser, UserResponseDTO.class);
    }

    // ********** Obtener todos los usuarios registrados ********** //
    @Override
    public List<UserResponseDTO> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());
    }

    // ********** Buscar usuario por ID ********** //
    @Override
    public UserResponseDTO findById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario con ID: " + id + " no encontrado"));

        return modelMapper.map(user, UserResponseDTO.class);
    }

    // ********** Buscar usuario por nombre ********** //
    @Override
    public UserResponseDTO findByName(String name) {
        User user = userRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new NoSuchElementException("Usuario con nombre: " + name + " no encontrado"));

        return modelMapper.map(user, UserResponseDTO.class);
    }

    // ********** Buscar usuario por correo electrónico ********** //
    @Override
    public UserResponseDTO findByEmail(String email) {
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new NoSuchElementException("Usuario con correo: " + email + " no encontrado"));

        return modelMapper.map(user, UserResponseDTO.class);
    }

    // ********** Obtener usuarios filtrados por ID de rol ********** //
    @Override
    public List<UserResponseDTO> findByRoleId(Integer roleId) {
        return userRepository.findByRoleId(roleId)
                .stream()
                .map(users -> modelMapper.map(users, UserResponseDTO.class))
                .collect(Collectors.toList());
    }

    // ********** Obtener usuarios por fecha de registro ********** //
    @Override
    public List<UserResponseDTO> findByRegistrationDate(LocalDate date) {
        // Definir el rango entre el inicio y el final del día
        LocalDateTime start = date.atStartOfDay();                             // 00:00:00
        LocalDateTime end = date.plusDays(1).atStartOfDay();        // 00:00:00 del día siguiente

        // Buscar usuarios cuya fecha de registro esté dentro del rango
        return userRepository.findByRegistrationDateBetween(start, end)
                .stream()
                .map(users -> modelMapper.map(users, UserResponseDTO.class))
                .collect(Collectors.toList());
    }

    // ********** Actualizar datos de un usuario existente ********** //
    @Override
    public UserResponseDTO updateUser(Integer id, UserRequestDTO userDto) {
        // Validar datos antes de actualizar
        userValidator.validateBeforeUpdate(id, userDto);

        // Buscar el usuario existente por ID
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        // Actualizar campos del usuario con los nuevos valores del DTO
        existingUser.setName(userDto.getName());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setPassword(userDto.getPassword());

        // Guardar cambios en la base de datos
        User updatedUser = userRepository.save(existingUser);

        // Retornar el usuario actualizado en forma de DTO
        return modelMapper.map(updatedUser, UserResponseDTO.class);
    }

    // ********** Actualizar rol de un usuario existente ********** //
    @Override
    public void changeUserRole(Integer userId, Integer newRoleId) {
        // Buscar el usuario
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        //Buscar el nuevo rol
        Role newRole = roleRepository.findById(newRoleId)
                .orElseThrow(() -> new NoSuchElementException("Rol no encontrado"));

        //Asignar el nuevo rol
        user.setRole(newRole);

        //Guardar cambios
        userRepository.save(user);
    }


    // ********** Eliminar un usuario por su ID ********** //
    @Override
    public void deleteUser(Integer id) {
        // Buscar el usuario por ID
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        // Eliminar el usuario de la base de datos
        userRepository.delete(existingUser);
    }
}
