package com.hechoconamor.hcaapi.sales.sale.dtos;

import com.hechoconamor.hcaapi.sales.sale.enums.SaleStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleResponseDTO {

    private Integer id;
    private LocalDateTime fecha;

    private Integer orderId;

    private Integer clientId;
    private String clientNombre;
    private String clientEmail;

    private BigDecimal total;
    private String metodoPago;
    private SaleStatus estado;
}
