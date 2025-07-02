package com.hechoconamor.hcaapi.supplies.s_movement.repository;

import com.hechoconamor.hcaapi.supplies.s_movement.entity.SupplyMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplyMovementRepository extends JpaRepository<SupplyMovement, Integer> {

    @Query("""
    SELECT COALESCE(SUM(
        CASE
            WHEN m.movementType IN ('INGRESO', 'DEVOLUCION', 'AJUSTE_POSITIVO') THEN m.quantity
            WHEN m.movementType IN ('SALIDA', 'VENTA', 'AJUSTE_NEGATIVO') THEN -m.quantity
            ELSE 0
        END
    ), 0)
    FROM SupplyMovement m
    WHERE m.supply.id = :supplyId
""")
    Integer calculateCurrentStock(@Param("supplyId") Integer supplyId);

}
