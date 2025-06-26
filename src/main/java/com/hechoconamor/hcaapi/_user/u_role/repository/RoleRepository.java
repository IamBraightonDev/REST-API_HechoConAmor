package com.hechoconamor.hcaapi._user.u_role.repository;

import com.hechoconamor.hcaapi._user.u_role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByNameIgnoreCase(String name);

}
