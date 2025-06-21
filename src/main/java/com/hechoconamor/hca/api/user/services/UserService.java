package com.hechoconamor.hca.api.user.services;

import com.hechoconamor.hca.api.user.entity.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserService {

    User registerUser(User user);

    List<User> findAllUsers();

    Optional<User> findById(Integer id);

    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);

    List<User> findByRole(Integer idRole);

    List<User> findByRegistrationDate(LocalDate date);

    Optional<User> updateUser(Integer id, User user);

    boolean deleteUser(Integer id);

}
