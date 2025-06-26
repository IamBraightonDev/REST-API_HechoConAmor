package com.hechoconamor.hcaapi._product.p_size.repository;

import com.hechoconamor.hcaapi._product.p_size.entity.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize, Integer> {

    Optional<ProductSize> findByNameIgnoreCase(String name);

}
