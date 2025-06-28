package com.hechoconamor.hcaapi.products.p_status.repository;

import com.hechoconamor.hcaapi.products.p_status.entity.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductStatusRepository extends JpaRepository<ProductStatus, Integer> {

    Optional<ProductStatus> findByNameIgnoreCase(String name);

}
