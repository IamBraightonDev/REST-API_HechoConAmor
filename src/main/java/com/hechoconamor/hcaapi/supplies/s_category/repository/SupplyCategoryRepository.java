package com.hechoconamor.hcaapi.supplies.s_category.repository;

import com.hechoconamor.hcaapi.supplies.s_category.entity.SupplyCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplyCategoryRepository extends JpaRepository<SupplyCategory, Integer> {

    Optional<SupplyCategory> findByNameIgnoreCase(String name);

}
