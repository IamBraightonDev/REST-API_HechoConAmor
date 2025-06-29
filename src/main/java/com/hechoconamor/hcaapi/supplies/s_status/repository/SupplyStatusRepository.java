package com.hechoconamor.hcaapi.supplies.s_status.repository;

import com.hechoconamor.hcaapi.supplies.s_status.entity.SupplyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplyStatusRepository extends JpaRepository<SupplyStatus, Integer> {

    Optional<SupplyStatus> findByNameIgnoreCase(String name);

}
