package com.hechoconamor.hca.api.user.services.impl;

import com.hechoconamor.hca.api.user.entity.User;
import com.hechoconamor.hca.api.user.repositoy.UserRepository;
import com.hechoconamor.hca.api.user.services.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(User user) {
        if (userRepository.findByNameIgnoreCase(user.getName()).isPresent()) {        // Validar si el nombre ya existe
            throw new IllegalArgumentException("Este nombre de usuario ya esta en uso");
        }
        if (userRepository.findByEmailIgnoreCase(user.getEmail()).isPresent()) {      // Validar si el email ya existe
            throw new IllegalArgumentException("Este email ya está registrado");
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByName(String name) {
        return userRepository.findByNameIgnoreCase(name);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }

    @Override
    public List<User> findByRole(Integer idRole) {
        return userRepository.findByRoleId(idRole);
    }

    @Override
    public List<User> findByRegistrationDate(LocalDate date) {
        return userRepository.findByRegistrationDate(date);
    }

    @Override
    public Optional<User> updateUser(Integer id, User user) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setName(user.getName());
                    existingUser.setName(user.getEmail());
                    existingUser.setPassword(user.getPassword());
                    existingUser.setRole(user.getRole());
                    return userRepository.save(existingUser);
                });
        // Si el repositorio no encuentra el user, .map no se ejecuta y devuelve Optional.empty()
    }

    @Override
    public boolean deleteUser(Integer id) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    userRepository.delete(existingUser);
                    return true;
                })
                .orElse(false);
    }
}
