package com.hechoconamor.hcaapi.sales.sale.repository;

import com.hechoconamor.hcaapi.orders.order.entity.Order;
import com.hechoconamor.hcaapi.sales.sale.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Integer> {

    boolean existsByOrder(Order order);

}
