package com.hechoconamor.hcaapi.supplies.supply.repository;

import com.hechoconamor.hcaapi.supplies.supply.entity.Supply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupplyRepository extends JpaRepository<Supply, Integer> {

    Optional<Supply> findByNameIgnoreCase(String name);

}
