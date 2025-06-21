package com.hechoconamor.hca.api.user.repositoy;

import com.hechoconamor.hca.api.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByNameIgnoreCase(String name);

    Optional<User> findByEmailIgnoreCase(String email);

    List<User> findByRole(Integer idRole);

    @Query("SELECT u FROM User u WHERE DATE(u.registrationDate) = :date")
    List<User> findByRegistrationDate(@Param("date") LocalDate date);

}
