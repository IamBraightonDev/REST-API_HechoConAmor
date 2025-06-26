package com.hechoconamor.hcaapi._user.u_user.services;

import com.hechoconamor.hcaapi._user.u_user.dtos.UserRequestDTO;
import com.hechoconamor.hcaapi._user.u_user.dtos.UserResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    UserResponseDTO registerUser(UserRequestDTO userDto);

    List<UserResponseDTO> findAllUsers();

    UserResponseDTO findById(Integer id);

    UserResponseDTO findByName(String name);

    UserResponseDTO findByEmail(String email);

    List<UserResponseDTO> findByRoleId(Integer roleId);

    List<UserResponseDTO> findByRegistrationDate(LocalDate date);

    UserResponseDTO updateUser(Integer id, UserRequestDTO userDto);

    void changeUserRole(Integer userId, Integer newRoleId);

    void deleteUser(Integer id);

}
