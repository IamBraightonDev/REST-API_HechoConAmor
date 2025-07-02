package com.hechoconamor.hcaapi.orders.order_detail.repository;

import com.hechoconamor.hcaapi.orders.order_detail.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {



}
