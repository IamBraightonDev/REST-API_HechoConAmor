package com.hechoconamor.hcaapi.sales.sale.validator;

import com.hechoconamor.hcaapi.orders.order.entity.Order;
import com.hechoconamor.hcaapi.orders.order.repository.OrderRepository;
import com.hechoconamor.hcaapi.sales.sale.repository.SaleRepository;
import com.hechoconamor.hcaapi.shared.exceptions.BadRequestException;
import com.hechoconamor.hcaapi.shared.exceptions.ConflictException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaleValidator {

    private final OrderRepository orderRepository;
    private final SaleRepository saleRepository;

    public Order validateOrderAvailable(Integer orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BadRequestException("Pedido no encontrado"));

        boolean yaFacturado = saleRepository.existsByOrder(order);
        if (yaFacturado) {
            throw new ConflictException("Este pedido ya ha sido facturado");
        }

        return order;
    }
}