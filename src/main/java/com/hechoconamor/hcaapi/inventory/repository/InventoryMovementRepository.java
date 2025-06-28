package com.hechoconamor.hcaapi.inventory.repository;

import com.hechoconamor.hcaapi.inventory.entity.InventoryMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InventoryMovementRepository extends JpaRepository<InventoryMovement, Integer> {

    @Query("SELECT COALESCE(SUM(m.quantity), 0) FROM InventoryMovement m WHERE m.product.id = :productId")
    Integer findStockByProductId(@Param("productId") Integer productId);

}
