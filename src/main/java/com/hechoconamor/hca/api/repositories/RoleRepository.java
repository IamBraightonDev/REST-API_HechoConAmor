package com.hechoconamor.hca.api.repositories;

import com.hechoconamor.hca.api.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByNameIgnoreCase(String name);

}
