package com.hechoconamor.hcaapi._product.p_color.repository;

import com.hechoconamor.hcaapi._product.p_color.entity.ProductColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductColorRepository extends JpaRepository<ProductColor, Integer> {

    Optional<ProductColor> findByNameIgnoreCase(String name);

}
