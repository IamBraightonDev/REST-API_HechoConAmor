package com.hechoconamor.hcaapi.sales.sale.mapper;

import com.hechoconamor.hcaapi.orders.client.entity.Client;
import com.hechoconamor.hcaapi.orders.order.entity.Order;
import com.hechoconamor.hcaapi.sales.sale.dtos.SaleResponseDTO;
import com.hechoconamor.hcaapi.sales.sale.entity.Sale;
import com.hechoconamor.hcaapi.sales.sale.enums.SaleStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class SaleMapper {

    public Sale toEntity(Order order, Client client, BigDecimal total, String metodoPago) {
        return Sale.builder()
                .fecha(LocalDateTime.now())
                .estado(SaleStatus.PAGADA) // o PENDIENTE, según tu flujo
                .total(total)
                .metodoPago(metodoPago)
                .order(order)
                .client(client)
                .build();
    }

    public SaleResponseDTO toResponseDTO(Sale sale) {
        return SaleResponseDTO.builder()
                .id(sale.getId())
                .fecha(sale.getFecha())
                .estado(sale.getEstado())
                .total(sale.getTotal())
                .metodoPago(sale.getMetodoPago())

                .orderId(sale.getOrder().getId())
                .clientId(sale.getClient().getId())
                .clientNombre(sale.getClient().getNombre())
                .clientEmail(sale.getClient().getEmail())
                .build();
    }
}
