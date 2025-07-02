package com.hechoconamor.hcaapi.orders.order_detail.services;

import com.hechoconamor.hcaapi.orders.order_detail.dtos.OrderDetailRequestDTO;
import com.hechoconamor.hcaapi.orders.order_detail.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {

    List<OrderDetail> buildValidatedDetails(List<OrderDetailRequestDTO> requestList);

}
