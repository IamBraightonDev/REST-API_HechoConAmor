package com.hechoconamor.hcaapi.productions.p_detail.entity;

import com.hechoconamor.hcaapi.productions.production.entity.Production;
import com.hechoconamor.hcaapi.products.product.entity.Product;
import com.hechoconamor.hcaapi.supplies.supply.entity.Supply;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Audited
@Entity
@Table(name = "production_details")
public class ProductionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "production_id")
    private Production production;

    @ManyToOne(optional = false)
    @JoinColumn(name = "supply_id")
    private Supply supply;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity_used", nullable = false)
    private Integer quantityUsed;

    @Column(name = "quantity_produced", nullable = false)
    private Integer quantityProduced;

}
