package com.hechoconamor.hcaapi.supplies.s_restock.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupplyRestockResponseDTO {

    private Integer id;

    private Integer supplyId;
    private String supplyName;

    private Integer quantity;

    private String reason;

    private String status;

    private LocalDateTime requestDate;
    private LocalDateTime approvalDate;

}
