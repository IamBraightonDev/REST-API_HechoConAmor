package com.hechoconamor.hcaapi.products.product.entity;

import com.hechoconamor.hcaapi.products.p_category.entity.ProductCategory;
import com.hechoconamor.hcaapi.products.p_color.entity.ProductColor;
import com.hechoconamor.hcaapi.products.p_material.entity.ProductMaterial;
import com.hechoconamor.hcaapi.products.p_size.entity.ProductSize;
import com.hechoconamor.hcaapi.products.p_status.entity.ProductStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Audited
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", unique = true, nullable = false, length = 150)
    private String name;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "stock", nullable = false)
    private Integer stock; // NO se llenará desde DTO, solo desde lógica

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_category", nullable = false)
    private ProductCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_color", nullable = false)
    private ProductColor color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_material", nullable = false)
    private ProductMaterial material;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_size", nullable = false)
    private ProductSize size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_status", nullable = false)
    private ProductStatus status;

}
