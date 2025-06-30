package com.hechoconamor.hcaapi.supplies.s_movement.entity;

import com.hechoconamor.hcaapi.supplies.s_movement.enums.MovementType;
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
@Table(name = "supply_movement")
public class SupplyMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Enumerated
    @Column(name = "movement_type", nullable = false, length = 50)
    private MovementType movementType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "supply_id", nullable = false)
    private Supply supply;

    @Column(name = "reason")
    private String reason;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

}
