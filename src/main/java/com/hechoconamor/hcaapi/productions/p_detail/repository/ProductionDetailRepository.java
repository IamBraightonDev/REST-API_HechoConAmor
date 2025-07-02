package com.hechoconamor.hcaapi.productions.p_detail.repository;

import com.hechoconamor.hcaapi.productions.p_detail.entity.ProductionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductionDetailRepository extends JpaRepository<ProductionDetail, Integer> {

    List<ProductionDetail> findByProductionId(Integer productionId);

}
