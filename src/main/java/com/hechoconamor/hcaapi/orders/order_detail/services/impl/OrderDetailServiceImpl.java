package com.hechoconamor.hcaapi.orders.order_detail.services.impl;

import com.hechoconamor.hcaapi.orders.order_detail.dtos.OrderDetailRequestDTO;
import com.hechoconamor.hcaapi.orders.order_detail.entity.OrderDetail;
import com.hechoconamor.hcaapi.orders.order_detail.mapper.OrderDetailMapper;
import com.hechoconamor.hcaapi.orders.order_detail.services.OrderDetailService;
import com.hechoconamor.hcaapi.orders.order_detail.validator.OrderDetailValidator;
import com.hechoconamor.hcaapi.products.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailValidator validator;
    private final OrderDetailMapper mapper;

    @Override
    public List<OrderDetail> buildValidatedDetails(List<OrderDetailRequestDTO> requestList) {
        return requestList.stream()
                .map(dto -> {
                    Product product = validator.validateProductAndStock(dto);
                    return mapper.toEntity(product, dto.getCantidad());
                })
                .collect(Collectors.toList());
    }
}