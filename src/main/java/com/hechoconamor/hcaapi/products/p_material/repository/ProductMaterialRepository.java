package com.hechoconamor.hcaapi.products.p_material.repository;

import com.hechoconamor.hcaapi.products.p_material.entity.ProductMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductMaterialRepository extends JpaRepository<ProductMaterial, Integer> {

    Optional<ProductMaterial> findByNameIgnoreCase(String name);

}
