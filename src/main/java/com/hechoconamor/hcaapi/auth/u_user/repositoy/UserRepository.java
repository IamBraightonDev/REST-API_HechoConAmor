package com.hechoconamor.hcaapi.auth.u_user.repositoy;

import com.hechoconamor.hcaapi.auth.u_user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByNameIgnoreCase(String name);

    Optional<User> findByEmailIgnoreCase(String email);

    List<User> findByRoleId(Integer roleId);

    @Query("SELECT u FROM User u WHERE u.registrationDate >= :start AND u.registrationDate < :end")
    List<User> findByRegistrationDateBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

}
