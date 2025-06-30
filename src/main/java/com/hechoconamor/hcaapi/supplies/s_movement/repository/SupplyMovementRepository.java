package com.hechoconamor.hcaapi.supplies.s_movement.repository;

import com.hechoconamor.hcaapi.supplies.s_movement.entity.SupplyMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplyMovementRepository extends JpaRepository<SupplyMovement, Integer> {

    Optional<SupplyMovement> findByNameIgnoreCase(String name);

    @Query("""
        SELECT COALESCE(SUM(
            CASE
                WHEN m.type = 'INGRESO' THEN m.quantity
                WHEN m.type = 'DEVOLUCION' THEN m.quantity
                WHEN m.type = 'AJUSTE_POSITIVO' THEN m.quantity
                WHEN m.type = 'SALIDA' THEN -m.quantity
                WHEN m.type = 'AJUSTE_NEGATIVO' THEN -m.quantity
                ELSE 0
            END
        ), 0)
        FROM SupplyMovement m
        WHERE m.supply.id = :supplyId
    """)
    Integer calculateCurrentStock(@Param("supplyId") Integer supplyId);

}
