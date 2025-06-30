package com.hechoconamor.hcaapi.products.inventory_movement.repository;

import com.hechoconamor.hcaapi.products.inventory_movement.entity.InventoryMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InventoryMovementRepository extends JpaRepository<InventoryMovement, Integer> {

    @Query("""
        SELECT COALESCE(SUM(
            CASE
                WHEN m.type = 'INGRESO' THEN m.quantity
                WHEN m.type = 'DEVOLUCION' THEN m.quantity
                WHEN m.type = 'AJUSTE' THEN m.quantity
                WHEN m.type = 'SALIDA' THEN -m.quantity
                ELSE 0
            END
        ), 0)
        FROM InventoryMovement m
        WHERE m.product.id = :productId
    """)
    Integer findStockByProductId(@Param("productId") Integer productId);

}
