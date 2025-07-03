package com.hechoconamor.hcaapi.sales.sale.services.impl;

import com.hechoconamor.hcaapi.orders.client.entity.Client;
import com.hechoconamor.hcaapi.orders.order.entity.Order;
import com.hechoconamor.hcaapi.orders.order_detail.entity.OrderDetail;
import com.hechoconamor.hcaapi.sales.sale.dtos.SaleRequestDTO;
import com.hechoconamor.hcaapi.sales.sale.dtos.SaleResponseDTO;
import com.hechoconamor.hcaapi.sales.sale.entity.Sale;
import com.hechoconamor.hcaapi.sales.sale.mapper.SaleMapper;
import com.hechoconamor.hcaapi.sales.sale.repository.SaleRepository;
import com.hechoconamor.hcaapi.sales.sale.services.SaleService;
import com.hechoconamor.hcaapi.sales.sale.validator.SaleValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final SaleValidator saleValidator;
    private final SaleMapper saleMapper;

    @Override
    public SaleResponseDTO create(SaleRequestDTO dto) {
        // 1. Validar que el pedido exista y no haya sido facturado
        Order order = saleValidator.validateOrderAvailable(dto.getOrderId());

        // 2. Obtener cliente del pedido
        Client client = order.getClient();

        // 3. Calcular total del pedido
        BigDecimal total = order.getDetalles().stream()
                .map(OrderDetail::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 4. Construir y guardar venta
        Sale sale = saleMapper.toEntity(order, client, total, dto.getMetodoPago());
        sale = saleRepository.save(sale);

        // 5. Responder
        return saleMapper.toResponseDTO(sale);
    }

    @Override
    public SaleResponseDTO getById(Integer id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Venta no encontrada"));
        return saleMapper.toResponseDTO(sale);
    }

    @Override
    public List<SaleResponseDTO> getAll() {
        return saleRepository.findAll().stream()
                .map(saleMapper::toResponseDTO)
                .toList();
    }

    @Override
    public void delete(Integer id) {
        if (!saleRepository.existsById(id)) {
            throw new NoSuchElementException("Venta no encontrada");
        }
        saleRepository.deleteById(id);
    }
}