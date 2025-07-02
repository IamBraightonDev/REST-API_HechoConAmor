package com.hechoconamor.hcaapi.orders.order_detail.mapper;

import com.hechoconamor.hcaapi.orders.order_detail.dtos.OrderDetailResponseDTO;
import com.hechoconamor.hcaapi.orders.order_detail.entity.OrderDetail;
import com.hechoconamor.hcaapi.products.product.entity.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class OrderDetailMapper {

    public OrderDetailResponseDTO toResponseDTO(OrderDetail entity) {
        return OrderDetailResponseDTO.builder()
                .id(entity.getId())
                .productId(entity.getProduct().getId())
                .productNombre(entity.getProduct().getName())
                .cantidad(entity.getCantidad())
                .precioUnitario(entity.getPrecioUnitario())
                .subtotal(entity.getSubtotal())
                .build();
    }

    public OrderDetail toEntity(Product product, Integer cantidad) {
        BigDecimal precioUnitario = product.getPrice();
        BigDecimal subtotal = precioUnitario.multiply(BigDecimal.valueOf(cantidad))
                .setScale(2, RoundingMode.HALF_UP);

        return OrderDetail.builder()
                .product(product)
                .cantidad(cantidad)
                .precioUnitario(precioUnitario)
                .subtotal(subtotal)
                .build();
    }
}