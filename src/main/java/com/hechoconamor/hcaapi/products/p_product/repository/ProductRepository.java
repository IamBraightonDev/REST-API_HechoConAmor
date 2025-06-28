package com.hechoconamor.hcaapi.products.p_product.repository;

import com.hechoconamor.hcaapi.products.p_product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findByNameIgnoreCase(String name);

    List<Product> findByCategory_NameIgnoreCase(String name);



}
