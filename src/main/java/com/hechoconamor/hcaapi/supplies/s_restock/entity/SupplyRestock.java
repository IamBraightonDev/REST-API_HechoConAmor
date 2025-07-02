package com.hechoconamor.hcaapi.supplies.s_restock.entity;

import com.hechoconamor.hcaapi.supplies.s_restock.enums.RestockStatus;
import com.hechoconamor.hcaapi.supplies.supply.entity.Supply;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Audited
@Entity
@Table(name = "supply_restock")
public class SupplyRestock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "supply_id", nullable = false)
    private Supply supply;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20, nullable = false)
    private RestockStatus status; // PENDIENTE, APROBADO, RECHAZADO

    @Column(name = "reason")
    private String reason;

    @Column(name = "request_date", nullable = false)
    private LocalDateTime requestDate;

    @Column(name = "approval_date")
    private LocalDateTime approvalDate;
}