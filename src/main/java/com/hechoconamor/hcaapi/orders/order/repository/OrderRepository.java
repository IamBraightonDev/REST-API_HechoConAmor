package com.hechoconamor.hcaapi.orders.order.repository;

import com.hechoconamor.hcaapi.orders.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {



}
