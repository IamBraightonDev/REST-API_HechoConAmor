package com.hechoconamor.hcaapi.products.p_category.repository;

import com.hechoconamor.hcaapi.products.p_category.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    Optional<ProductCategory> findByNameIgnoreCase(String name);

}
