package com.hechoconamor.hcaapi.inventory.entity;

import com.hechoconamor.hcaapi.inventory.enums.MovementType;
import com.hechoconamor.hcaapi.products.p_product.entity.Product;
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
@Table(name = "inventory_movements")
public class InventoryMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "quantity", nullable = false)
    private Integer quantity; // + para entradas, - para salidas

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private MovementType type; // INGRESO, SALIDA, VENTA, DEVOLUCION, AJUSTE, etc.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "reason")
    private String reason;

}
