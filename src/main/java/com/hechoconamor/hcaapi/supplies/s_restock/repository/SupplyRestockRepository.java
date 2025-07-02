package com.hechoconamor.hcaapi.supplies.s_restock.repository;

import com.hechoconamor.hcaapi.supplies.s_restock.entity.SupplyRestock;
import com.hechoconamor.hcaapi.supplies.s_restock.enums.RestockStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplyRestockRepository extends JpaRepository<SupplyRestock, Integer> {

    // Buscar todas las solicitudes por estado
    List<SupplyRestock> findByStatus(RestockStatus status);

    // Buscar solicitudes por insumo
    List<SupplyRestock> findBySupplyId(Integer supplyId);

}
